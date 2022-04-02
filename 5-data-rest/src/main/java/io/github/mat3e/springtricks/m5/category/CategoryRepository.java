package io.github.mat3e.springtricks.m5.category;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = SimpleCategoryVM.class)
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "5")
interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    @RestResource(path = "active", rel = "active")
    List<Category> findByDeprecatedNot(@Param("state") boolean deprecated);

    @RestResource(path = "description-containing", rel = "descriptionContaining")
    List<Category> findByDescriptionContaining(String phrase);
}
