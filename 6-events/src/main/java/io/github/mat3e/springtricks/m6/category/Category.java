package io.github.mat3e.springtricks.m6.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PROTECTED;

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
