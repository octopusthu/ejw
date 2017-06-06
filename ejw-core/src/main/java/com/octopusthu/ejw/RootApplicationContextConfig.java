package com.octopusthu.ejw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.octopusthu.ejw.component.EjwProps;
import com.octopusthu.ejw.component.ExposedResourceBundleMessageSource;

@Configuration
@ComponentScan({ "${ejw.basePackages}" })
@PropertySource(value = { "classpath:ejw-core.properties" }, encoding = "UTF-8")
@EnableConfigurationProperties
public class RootApplicationContextConfig {

	// TODO: Caches

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySource() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public EjwProps ejwProps() {
		return new EjwProps();
	}

	@ConditionalOnProperty(value = "ejw.listeners.list", havingValue = "requestContextListener")
	@Bean
	public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistrationBean() {
		return new ServletListenerRegistrationBean<RequestContextListener>(new RequestContextListener());
	}

	@ConditionalOnProperty(value = "ejw.filters.list", havingValue = "characterEncodingFilter")
	@Bean
	public FilterRegistrationBean characterEncodingFilterRegistrationBean() {
		EjwProps.Filters.CharacterEncodingFilter props = ejwProps().getFilters().getCharacterEncodingFilter();
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setName(props.getName());
		bean.setFilter(new CharacterEncodingFilter());
		bean.addInitParameter("encoding", props.getEncoding());
		bean.addServletNames(props.getServletNames());
		bean.addUrlPatterns(props.getUrlPatterns());
		return bean;
	}

	@ConditionalOnProperty(value = "ejw.filters.list", havingValue = "shallowEtagHeaderFilter")
	@Bean
	public FilterRegistrationBean shallowEtagHeaderFilterRegistrationBean() {
		EjwProps.Filters.ShallowEtagHeaderFilter props = ejwProps().getFilters().getShallowEtagHeaderFilter();
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setName(props.getName());
		bean.setFilter(new ShallowEtagHeaderFilter());
		bean.addServletNames(props.getServletNames());
		bean.addUrlPatterns(props.getUrlPatterns());
		return bean;
	}

	@Bean
	public ExposedResourceBundleMessageSource messageSource(
			@Value("${ejw.messageSource.basenames:messages,messages_ejw_core}") String[] basenames,
			@Value("${ejw.messageSource.defaultEncoding:UTF-8}") String defaultEncoding,
			@Value("${ejw.messageSource.useCodeAsDefaultMessage:false}") boolean useCodeAsDefaultMessage) {
		ExposedResourceBundleMessageSource source = new ExposedResourceBundleMessageSource();
		source.setBasenames(basenames);
		source.setDefaultEncoding(defaultEncoding);
		source.setUseCodeAsDefaultMessage(useCodeAsDefaultMessage);
		return source;
	}

}