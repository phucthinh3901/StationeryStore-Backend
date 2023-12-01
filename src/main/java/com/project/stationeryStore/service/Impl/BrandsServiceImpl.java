package com.project.stationeryStore.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.stationeryStore.domain.dto.BrandsDto;
import com.project.stationeryStore.domain.inventory.Brands;
import com.project.stationeryStore.domain.payload.request.BrandRequest;
import com.project.stationeryStore.repository.BrandsRepository;
import com.project.stationeryStore.service.BrandsService;

@Service
public class BrandsServiceImpl implements BrandsService {

	@Autowired
	BrandsRepository brandsRepository;

	@Override
	public List<BrandsDto> createOrUpdate(BrandRequest request) {
		Brands brand = null;
		List<BrandsDto> result = new ArrayList<BrandsDto>();
		if (request.getId() == null) {
			brand = new Brands();
		}
		else {
			brand = brandsRepository.findById(request.getId()).orElse(null);
		}
		brand.setBrandName(request.getName());
		brand.setDescription(request.getDescription());
		brand.setIsActive(true);
		brandsRepository.save(brand);
		
		result = getBrands();
		
		return result;
	}
	@Override
	public List<BrandsDto> getBrands() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("brandName"));
		Page<Brands> brands = brandsRepository.findAll(pageable);
		
//		List<Brands> brands = brandsRepository.findAll();
		
		List<BrandsDto> result = new ArrayList<BrandsDto>();
		BrandsDto dto = null;
		for(Brands brand : brands) {
			dto = new BrandsDto();
			dto.setDescription(brand.getDescription());
			dto.setName(brand.getBrandName());
			dto.setIsActive(brand.getIsActive());
			dto.setId(brand.getId());
			result.add(dto);
		}
		return result;
	}

	@Override
	public Boolean removeBrand(Integer id) {

		Boolean result = false;
		Brands brand = brandsRepository.findById(id).orElse(null);
		if(brand != null) {
			brand.setIsActive(false);
			brandsRepository.save(brand);
			result = true;
		}
		return result;
	}

}
