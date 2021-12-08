package com.octopusthu.ejw.sample.multireadrequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

@Configuration
@Import({
    MultiReadRequestTestingController.class
})
public class MultiReadRequestTestingConfig {

    /**
     * A Spring Security filter chain to test {@link MultiReadHttpServletRequest}.
     *
     * @author octopusthu@gmail.com
     */
    @Order(Ordered.HIGHEST_PRECEDENCE + 1000)
    @EnableWebSecurity
    public static class MultiReadTestingWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/multi-read/**")
                .csrf().disable()
                .authorizeRequests(authorize -> authorize
                    .anyRequest().permitAll()
                );

            // Print form parameters before reading request body
            http.addFilterBefore(new FormParametersPrinterFilter(), ChannelProcessingFilter.class);

            // Wrap with MultiReadHttpServletRequest and read the request body
            http.addFilterAfter(new MultiReadRequestWrapperFilter(), ChannelProcessingFilter.class);

            // Read the request at the very end of the filter chain so as
            // not to interfere with the default Spring Security filters
            http.addFilterAfter(new MultiReadRequestTestingFilter(), SwitchUserFilter.class);

        }

    }
}
