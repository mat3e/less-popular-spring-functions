# Less popular Spring tricks: no Hibernate, rest repo, projections

Typical Spring project

* JPA (usually Hibernate ORM), `@Entity` classes here and there, Flyway for DB migrations
* Spring Data - SQL for above classes; especially `JpaRepository`
* Entity-DTO mapping, usually done by hand
* `@Controller` or `@RestController` exposing DTOs

This project

* Still with Flyway
* Still with Spring Data
* No JPA, no "manual" mappings, fancy REST (HATEOAS)
* And MOAR!

## Intro

Presentation with no slides? OK...

### But no memes?

![observe meme](./img/observe.jpg)

### Who am I?

* [linkedin.com/in/mateusz-chrzonstowski](https://www.linkedin.com/in/mateusz-chrzonstowski) // coding for $$$ since
  2013
* [twitter.com/\_mat3e\_](https://twitter.com/_mat3e_) // code, MMA, Dragon Ball
* [github.com/mat3e](https://github.com/mat3e) // coding, giving tech talks

### [github.com/mat3e/less-popular-spring-functions](https://github.com/mat3e/less-popular-spring-functions)

### Who are _they_?

* Rod Johnson - PhD in 19th-century piano music
* Adrian Colyer - pioneer of Aspect-oriented programming tools 