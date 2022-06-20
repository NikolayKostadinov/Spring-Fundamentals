package bg.manhattan.finalexam.service;


import bg.manhattan.finalexam.model.entity.Category;
import bg.manhattan.finalexam.model.entity.enums.CategoryNameEnum;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findByName(CategoryNameEnum category);
}
