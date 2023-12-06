package com.project.stationeryStore.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.stationeryStore.common.Constants;
import com.project.stationeryStore.domain.dto.FileUploadDto;
import com.project.stationeryStore.domain.dto.ProductDto;
import com.project.stationeryStore.domain.inventory.Brands;
import com.project.stationeryStore.domain.inventory.Categories;
import com.project.stationeryStore.domain.inventory.Images;
import com.project.stationeryStore.domain.inventory.Products;
import com.project.stationeryStore.domain.payload.request.ProductRequest;
import com.project.stationeryStore.domain.payload.response.ApiResponse;
import com.project.stationeryStore.repository.BrandsRepository;
import com.project.stationeryStore.repository.CategoryRepository;
import com.project.stationeryStore.repository.ImageRepository;
import com.project.stationeryStore.repository.ProductRepository;
import com.project.stationeryStore.service.FileService;
import com.project.stationeryStore.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	BrandsRepository brandsRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	FileService storageService;
	
	@Override
	public ApiResponse createOrUpdateProduct(MultipartFile[] files, ProductRequest request) {
		
		ApiResponse apiResponse = new ApiResponse();
		Products product = null;
		Brands brand = null;
		Categories category = null;	
		Images image = null;
		if(request.getBrandId() == null) {
			apiResponse.setMessage(Constants.NOT_FOUND_MSG);
			apiResponse.setSuccess(false);
			return apiResponse;
			
		}
		if(request.getCategoryId() == null) {
			apiResponse.setMessage(Constants.NOT_FOUND_MSG);
			apiResponse.setSuccess(false);
			return apiResponse;
		}
		List<Images> images = new ArrayList<>(); 
		List<ProductDto> result = new ArrayList<ProductDto>();
		if(request.getId() == null) {
			product = new Products();
			product.setUpdatedDate(null);
			product.setCreatedDate(new Date());
		}
		else {
			product = productRepository.findById(request.getId()).orElse(null);
			product.setUpdatedDate(new Date());
		}
		
		product.setDiscount(request.getDiscount());
		product.setPrice(request.getPrice());
		product.setDescription(request.getDescription());
		product.setQuanity(request.getQuantity());
		product.setSoldQuantity(0);
		product.setIsActive(true);
		product.setProductName(request.getProductName());
		
		brand = brandsRepository.findBrandByIdAndIsActive(request.getBrandId());
		product.setBrands(brand);
		
		category = categoryRepository.findCategoryByIdAndIsActive(request.getCategoryId());
		product.setCategories(category);
		productRepository.save(product);
		
		if(files != null) {
			List<FileUploadDto> fileUploadDtos = storageService.uploadFiles(files);
			for(FileUploadDto fileUploadDto : fileUploadDtos) {
				image = new Images();
				image.setImageName(fileUploadDto.getFileName());
				image.setImageUrl(fileUploadDto.getFileUrl());
				image.setProducts(product);
				images.add(image);
				imageRepository.save(image);
			}
		}
		result = getProducts();
		apiResponse.setData(result);
		apiResponse.setMessage(Constants.GET_SUCESS_MSG);
		apiResponse.setSuccess(true);
		return apiResponse;
	}

	@Override
	public List<ProductDto> getProducts() {	
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("productName"));
		Page<Products> products = productRepository.findListProductByIsAcitveAndPagination(pageable);
		List<ProductDto> result = new ArrayList<ProductDto>();
		ProductDto dto = null;
		List<Images> listImage = null;
		for(Products product : products) {
			listImage = new ArrayList<Images>();
			dto = new ProductDto();
			dto.setBrandId(product.getBrands().getId());
			dto.setId(product.getId());
			dto.setBrandName(product.getBrands().getBrandName());
			dto.setCategoryId(product.getCategories().getId());
			dto.setCategoryName(product.getCategories().getCategoryName());
			dto.setProductName(product.getProductName());
			dto.setDescription(product.getDescription());
			dto.setQuantity(product.getQuanity());
			dto.setDiscount(product.getDiscount());
			dto.setSoldQuantity(product.getSoldQuantity());
			dto.setPrice(product.getPrice());
			dto.setIsActive(product.getIsActive());
			dto.setAmount(product.getPrice() - (product.getPrice()*product.getDiscount()/100));
			for(Images image : product.getImages()) {		
				listImage.add(image);		
			}	
			dto.setImages(listImage);
			result.add(dto);
		}
		return result;
	}

	@Override
	public Boolean removeProduct(Integer id) {
		Boolean result = false;
		Products product = null;
		product = productRepository.findById(id).orElse(null);
		if(product != null) {
			product.setIsActive(false);
		}
		return result;
	}

	@Override
	public List<ProductDto> getProductById(Integer productId) {
		ProductDto dto = new ProductDto();
		List<ProductDto> listProduct = new ArrayList<ProductDto>();
		List<Images> listImage = new ArrayList<Images>();
		Products product = productRepository.findByProductById(productId);
		dto.setId(product.getId());
		dto.setBrandId(product.getBrands().getId());
		dto.setBrandName(product.getBrands().getBrandName());
		dto.setCategoryId(product.getCategories().getId());
		dto.setCategoryName(product.getCategories().getCategoryName());
		dto.setProductName(product.getProductName());
		dto.setDescription(product.getDescription());
		dto.setQuantity(product.getQuanity());
		dto.setSoldQuantity(product.getSoldQuantity());
		dto.setPrice(product.getPrice());
		dto.setDiscount(product.getDiscount());
		dto.setIsActive(product.getIsActive());
		for(Images images : product.getImages()) {
			listImage.add(images);
		}
		dto.setImages(listImage);
		listProduct.add(dto);
		return listProduct;
	}

}
