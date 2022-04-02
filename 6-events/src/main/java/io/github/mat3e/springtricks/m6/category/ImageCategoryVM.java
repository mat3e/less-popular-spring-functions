package io.github.mat3e.springtricks.m6.category;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "image", types = Category.class)
interface ImageCategoryVM {

    String getCode();

    String getDescription();

    String getImageUrl();
}
