# Less popular Spring functions

## 4-projections

Simple demo - just a single projection.  

```java
//                                                 👇
interface CategoryRepository extends Repository<Category, Long> {

    //        👇
    List<TextCategoryVM> findTextualCategoriesBy();
}

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table("categories")
class Category {

    @Id
    private Long id;
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    private String description;
    private String imageUrl;
    @Column("is_deprecated")
    private boolean deprecated;
}

// getters "compatible" with the Category class
interface TextCategoryVM {

    String getCode();

    String getName();

    String getDescription();
}
```

Further read:

* Baeldung: [Spring Data JPA Projections](https://www.baeldung.com/spring-data-jpa-projections)
* [Generic `findBy(Class<T> type)` projection](https://stackoverflow.com/questions/48441324/spring-data-jpa-generic-projection-findall)
