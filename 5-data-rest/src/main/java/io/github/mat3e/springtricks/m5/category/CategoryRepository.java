package io.github.mat3e.springtricks.m5.category;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@ConditionalOnModuleNumber("5")
@RepositoryRestResource(excerptProjection = SimpleCategoryVM.class)
interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    @RestResource(path = "active", rel = "active")
    List<Category> findByDeprecatedNot(@Param("state") boolean deprecated);

    @RestResource(path = "description-containing", rel = "descriptionContaining")
    List<Category> findByDescriptionContaining(String phrase);
}
