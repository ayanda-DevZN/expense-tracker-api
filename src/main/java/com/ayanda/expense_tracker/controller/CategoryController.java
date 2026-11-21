package com.ayanda.expense_tracker.controller;

import com.ayanda.expense_tracker.dto.CategoryRequest;
import com.ayanda.expense_tracker.dto.CategoryResponse;
import com.ayanda.expense_tracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

   private final CategoryService categoryService;

   public CategoryController(CategoryService categoryService){
    this.categoryService = categoryService;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public CategoryResponse create(@RequestParam Long userId, @Valid @RequestBody CategoryRequest request){
    return categoryService.create(userId, request);
   }

   @GetMapping
   public List<CategoryResponse> getAll(@RequestParam Long userId){
    return categoryService.getAll(userId);
   }

   @PutMapping("/{id}")
   public CategoryResponse update(@RequestParam Long userId, @PathVariable("id") Long categoryId, @Valid @RequestBody CategoryRequest request ){
    return categoryService.update(userId, categoryId, request);
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void delete(@RequestParam Long userId, @PathVariable("id") Long categoryId){
     categoryService.delete(userId, categoryId);
   }

}
