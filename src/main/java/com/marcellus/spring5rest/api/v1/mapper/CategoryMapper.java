package com.marcellus.spring5rest.api.v1.mapper;

import com.marcellus.spring5rest.api.v1.model.CategoryDTO;
import com.marcellus.spring5rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
