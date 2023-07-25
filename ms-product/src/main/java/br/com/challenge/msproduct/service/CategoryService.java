package br.com.challenge.msproduct.service;

import br.com.challenge.msproduct.exception.ResourceNotFoundException;
import br.com.challenge.msproduct.repository.CategoryRepository;
import br.com.challenge.msproduct.entity.Category;
import br.com.challenge.msproduct.payload.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> mapToDTO(category)).toList();
    }

    public CategoryDTO findCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", categoryId));
        return mapToDTO(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);

        Category createdCategory = categoryRepository.save(category);

        return mapToDTO(createdCategory);
    }


    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", categoryId));
        category.setId(categoryId);
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);

        return mapToDTO(category);
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(category);
    }

    private CategoryDTO mapToDTO(Category category) {
        return mapper.map(category, CategoryDTO.class);
    }
    private Category mapToEntity(CategoryDTO categoryDTO) {
        return mapper.map(categoryDTO, Category.class);
    }
}
