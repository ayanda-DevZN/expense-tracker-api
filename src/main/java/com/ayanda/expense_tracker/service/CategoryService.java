package com.ayanda.expense_tracker.service;

import com.ayanda.expense_tracker.dto.CategoryRequest;
import com.ayanda.expense_tracker.dto.CategoryResponse;
import com.ayanda.expense_tracker.entity.Category;
import com.ayanda.expense_tracker.entity.User;
import com.ayanda.expense_tracker.exception.ResourceNotFoundException;
import com.ayanda.expense_tracker.repository.CategoryRepository;
import com.ayanda.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CategoryResponse create(Long userId, CategoryRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Category category = new Category();
        category.setName(request.name());
        category.setUser(user);

        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAll(Long userId) {
        List<Category> categories = categoryRepository.findByUserId(userId);
        List<CategoryResponse> result = new ArrayList<>();
        for (Category category : categories) {
            result.add(toResponse(category));
        }
        return result;
    }

    @Transactional
    public CategoryResponse update(Long userId, Long categoryId, CategoryRequest request) {
        Category category = findOwnedCategory(userId, categoryId);
        category.setName(request.name());
        return toResponse(category);
    }

    @Transactional
    public void delete(Long userId, Long categoryId) {
        Category category = findOwnedCategory(userId, categoryId);
        categoryRepository.delete(category);
    }

    private Category findOwnedCategory(Long userId, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (!category.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Category not found");
        }
        return category;
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}