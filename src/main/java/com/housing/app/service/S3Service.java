package com.housing.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
  String uploadFile(String fileName, MultipartFile file);
}