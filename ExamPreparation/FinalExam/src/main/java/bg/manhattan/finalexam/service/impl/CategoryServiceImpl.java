package bg.manhattan.finalexam.service.impl;


import bg.manhattan.finalexam.model.entity.Category;
import bg.manhattan.finalexam.model.entity.enums.CategoryNameEnum;
import bg.manhattan.finalexam.repository.CategoryRepository;
import bg.manhattan.finalexam.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Category> findByName(CategoryNameEnum category) {
        return this.repository.findByName(category);
    }
}
