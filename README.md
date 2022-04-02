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