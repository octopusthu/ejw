package com.octopusthu.ejw.sample;

import com.octopusthu.ejw.sample.dummy.Dummy;
import com.octopusthu.ejw.sample.multireadrequest.MultiReadRequestTestingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * The sample application.
 * <p>
 * Auto component scan is disabled by referencing a placeholder class.
 *
 * @author octopusthu@gmail.com
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = Dummy.class)
@Import({
    DefaultWebSecurityConfig.class,
    MultiReadRequestTestingConfig.class
})
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

}
