package ru.practicum.explorewithme.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.dtomain.category.CategoryDto;
import ru.practicum.explorewithme.dtomain.category.NewCategoryDto;
import ru.practicum.explorewithme.log.ToLog;
import ru.practicum.explorewithme.mapper.CategoryMapper;
import ru.practicum.explorewithme.model.Category;
import ru.practicum.explorewithme.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Validated
@ToLog
public class AdminCategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto saveCategory(@RequestBody @Valid NewCategoryDto dto) {
        Category category = categoryService.saveCategory(categoryMapper.toCategoryNew(dto));
        return categoryMapper.toCategoryDto(category);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@Positive @PathVariable Long catId) {
        categoryService.deleteCategoryById(catId);
    }

    @PatchMapping("/{catId}")
    public CategoryDto changeCategory(@Positive @PathVariable Long catId,
                                      @Valid @RequestBody CategoryDto dto) {
        Category category = categoryService
                .changeCategory(catId, categoryMapper.toCategory(dto));
        return categoryMapper.toCategoryDto(category);
    }
}

