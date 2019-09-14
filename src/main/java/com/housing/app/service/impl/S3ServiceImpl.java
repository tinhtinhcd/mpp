package com.housing.app.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.housing.app.S3Config;
import com.housing.app.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3ServiceImpl implements S3Service {


    private final static Logger logger = LoggerFactory.getLogger("application-info");

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private S3Config s3Config;

    @Override
    public String uploadFile(String fileName, MultipartFile file) {

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        try {
            s3Client.putObject(new PutObjectRequest(s3Config.getBucket(), s3Config.getImageFolder()+"/"+fileName, file.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead));
            logger.info("===================== Upload File - Done! =====================");
        } catch (AmazonServiceException ase) {
            logger.error("Caught an AmazonServiceException from PUT requests, rejected reasons:");
        } catch (AmazonClientException ace) {
            logger.error("Caught an AmazonClientException: ");
            logger.error("Error Message: " + ace.getMessage());
        } catch (IOException e) {
            logger.error("File exception: ");
            logger.error("Error Message: " + e.getMessage());
        }
        return returnImageUrl(fileName);
    }

    private String returnImageUrl(String fileName) {
        StringBuilder fileUrl = new StringBuilder("https://");
        fileUrl.append(s3Config.getBucket());
        fileUrl.append(".s3.amazonaws.com/");
        fileUrl.append(s3Config.getImageFolder() + "/" + fileName);
        return fileUrl.toString();
    }
}