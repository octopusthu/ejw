# EJW
Easy Java Web Framework built upon spring-boot, spring-framework and spring-security.

## FEATURES
- Fully customizable property management in accordance with spring-boot's [Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)
- Out-of-the-box i18n support with a LocaleChangeInterceptor, a CookieLocaleResolver, and customizable locales
- Message sources are served in JSON format via a cached endpoint to become javascript-friendly.
- A convenient starter project made with Maven Overlay
- Production-ready examples of common Web development scenarios, including:
    - Form validation

## TODO
- phase out web.xml
- profile-aware static resources (e.g.: react.min.js)
- remove url prefixes (/f/\*\*, /b/\*\*, /a/\*\*)
- filter test sources
- properties
  - hierarchical @ConfigurationProperties

##### MVC
- find a replacement for JSP?
- revamp ControllerResult

##### Cache
- Ehcache non daemon thread
- startup time
- properties bean

