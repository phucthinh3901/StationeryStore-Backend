package com.project.stationeryStore.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.stationeryStore.domain.dto.CategoryDto;
import com.project.stationeryStore.domain.inventory.Categories;
import com.project.stationeryStore.domain.payload.request.CategoryRequest;
import com.project.stationeryStore.repository.CategoryRepository;
import com.project.stationeryStore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDto> createOrUpdateCategory(CategoryRequest request) {
		
		Categories category = null;
		List<CategoryDto> result = new ArrayList<CategoryDto>();
		if(request.getId() == null) {
			category = new Categories();
		}
		else {
			category = categoryRepository.findById(request.getId()).orElse(null);
		}
		category.setCategoryName(request.getCategoryName());
		category.setDescription(request.getDescription());
		category.setImage(request.getImageName());
		category.setIsActive(true);
		
		categoryRepository.save(category);
		result = getCategories();
		
		return result;		
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("categoryName"));
		Page<Categories> listCategoriesIsActiveTrue = categoryRepository.findCategoriesByIsAcitve(pageable);
		List<CategoryDto> result = new ArrayList<CategoryDto>();
	
		CategoryDto dto = null;
		for(Categories cate : listCategoriesIsActiveTrue) {
			dto = new CategoryDto();
			dto.setId(cate.getId());
			dto.setCategoryName(cate.getCategoryName());
			dto.setDescription(cate.getDescription());
			dto.setImageName(cate.getImage());
			dto.setIsActive(cate.getIsActive());
			result.add(dto);
		}

		return result;
	}

	@Override
	public Boolean removeCategory(Integer id) {
		Boolean result = false;
		Categories category = categoryRepository.findById(id).orElse(null);
		if(category != null) {
			category.setIsActive(false);
			categoryRepository.save(category);
			result = true;
		}
		
		
		return result;
	}

}
