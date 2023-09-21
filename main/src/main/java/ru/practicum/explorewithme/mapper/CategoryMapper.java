package ru.practicum.explorewithme.mapper;

import org.mapstruct.Mapper;
import ru.practicum.explorewithme.dtomain.category.CategoryDto;
import ru.practicum.explorewithme.dtomain.category.NewCategoryDto;
import ru.practicum.explorewithme.model.Category;

import javax.validation.Valid;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(@Valid CategoryDto newCategoryDto);

    Category toCategoryNew(@Valid NewCategoryDto newCategoryDto);

    CategoryDto toCategoryDto(Category category);
}