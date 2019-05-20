package com.octopusthu.ejw.sample.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.instrument.classloading.LoadTimeWeaver;

//@EnableCaching(mode = AdviceMode.ASPECTJ)
//@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
@EnableCaching
@Configuration
public class SampleCacheConfig {
}
