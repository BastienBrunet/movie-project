package com.mouvie.client.tools.factory;

import com.mouvie.client.dto.model.category.CategoryInputDto;
import com.mouvie.library.model.Category;

public class CategoryFactory {

    public static Category createCategory(CategoryInputDto inputDto){
        return new Category()
                .setName(inputDto.getName());
               
    }

    public static Category updateCategory(Category category, CategoryInputDto inputDto){
        return category
                .setName(inputDto.getName());
                
    }
}
