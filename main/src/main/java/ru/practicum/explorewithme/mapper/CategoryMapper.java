package ru.practicum.explorewithme.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.dtomain.category.CategoryDto;
import ru.practicum.explorewithme.dtomain.category.NewCategoryDto;
import ru.practicum.explorewithme.model.Category;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {
    Category toCategory(NewCategoryDto newCategoryDto);

    CategoryDto toCategoryDto(Category category);
}