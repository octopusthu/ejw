# spring-framework-reference ([htmlsingle](https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/), [pdf](https://docs.spring.io/spring/docs/current/spring-framework-reference/pdf/spring-framework-reference.pdf))

## [5. Aspect Oriented Programming with Spring](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop)

- [5.10.4. Load-time Weaving with AspectJ in the Spring Framework](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-aj-ltw)

## [7. Task Execution and Scheduling](https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling)

- > As with Spring’s TaskExecutor abstraction, the primary benefit of the TaskScheduler arrangement is that an application’s scheduling needs are decoupled from the deployment environment. This abstraction level is particularly relevant when deploying to an application server environment where threads should not be created directly by the application itself.

- > The main idea is that, when a task is submitted, the executor first tries to use a free thread if the number of active threads is currently less than the core size. If the core size has been reached, the task is added to the queue, as long as its capacity has not yet been reached. Only then, if the queue’s capacity has been reached, does the executor create a new thread beyond the core size. If the max size has also been reached, then the executor rejects the task.

  - See [Java API](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadPoolExecutor.html) for more information.

## [8. Cache Abstraction](https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#cache)

## 22. Web MVC framework

> An application should have only one configuration extending DelegatingWebMvcConfiguration or a single @EnableWebMvc annotated class, since they both register the same underlying beans.
