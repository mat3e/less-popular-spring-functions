# Less popular Spring functions

## 0 package-private FTW

* Spring is great with a default Java access
* Little to nothing needs to be public (e.g. `@Transactional` and `@EventListener`)
* Register main service in each package
   * Fowler's [Service Layer](https://martinfowler.com/eaaCatalog/serviceLayer.html)
   * Nabrdalik's [mid-sized building blocks](https://www.youtube.com/watch?v=KrLFs6f2bOA)
   * [PL] Krzysztof Ślusarski's [_Porty, adaptery, CQRS, Event Sourcing, DDD… w Springu?_](https://www.youtube.com/watch?v=Da42_gVqDKE)
* We shouldn't put internal "helper" services (mappers, generators, etc.) in context. Create them by hand and make
    package-private (internal implementation details)!
* You might still use (`public`) interfaces and make all the implementations with package-private access and Spring 

Additional info
> Spring Boot registers beans starting from the main class package. It's also possible to use the same package in different modules

## Stop overusing Spring context

![Ja tam wolę mieć kontrolę nad tworzeniem obiektów](./img/janusz.jpg)

* Input and output components (repo, HTTP clients) should be taken from context ([_Ports & Adapters_ architecture
  a.k.a. _Clean Architecture_](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/))
* Using `@Component` and its meta-annotations (`@Service`, `@Repository`) can work, but makes our components aware of
  Spring and concrete implementations
   ```java
  @Service // <- we know about Spring!
  class CountryService {
    private final CountryRepository repository;
    private final CitySlugGenerator slugGenerator;
   
    CountryService(CountryRepository repository) {
      this.repository = repository;
      // we know about the impl --v
      this.slugGenerator = new CitySlugGenerator();
    }
    // ...
  }
   ```
* For more (than one) constructors we also need to put `@Autowired` annotation on the one for Spring
* With `@Bean` definitions (instead of `@Component` ones) we call the proper constructor and we can make components
  Spring and implementations agnostic
* We can also use Spring features themselves conditionally
   ```java
   @Configuration
   @RequiredArgsConstructor
   class EventConfiguration {
     private final EventHandler eventHandler;

     @Async // <- does nothing until below configuration registered
     @EventListener
     public void on(Event event) {
       eventHandler.handle(event);
     }
   }

   @EnableAsync // <- crucial part
   @Configuration
   @Profile("!test")
   @ConditionalOnProperty(
     name = "spring.main.web-application-type",
     havingValue = "servlet"
   )
   // v- registered only for non-test profiles when "servlet" app
   class AsyncConfiguration { }
   ``` 
