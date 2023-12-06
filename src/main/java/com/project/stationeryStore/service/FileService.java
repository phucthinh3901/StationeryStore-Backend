package com.project.stationeryStore.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.stationeryStore.domain.dto.FileUploadDto;


public interface FileService {
	public List<FileUploadDto> uploadFiles(MultipartFile[] files);
}
