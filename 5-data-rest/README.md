# Less popular Spring functions

## 5-data-rest

⚠️ Disclaimer ⚠️
> I'd personally use Rest Repositories just for read operations. Preferably built on top of the pure `Repository` interface. It can be a powerful thing for "query" part of the system, especially combined with projections. However, in practice it seems sooner or later you'd move towards a traditional approach which is more customizable than `@RepositoryRestResource` and `@RestResource` options, default interface methods and even `RepositoryRestController`.

[Spring Data REST](https://spring.io/projects/spring-data-rest) &
its [detailed documentation](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#reference).
