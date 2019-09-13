package com.housing.app;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class S3Config {

  @Value("#{environment.AWS_ACCESS_KEY}")
  private String awsKeyId;

  @Value("#{environment.AWS_SECRET_KEY}")
  private String awsSecretKey;

  @Value("#{environment.AWS_REGION}")
  private String region;

  @Value("#{environment.BUCKET}")
  private String bucket;

  @Value("#{environment.IMAGE_FOLDER}")
  private String imageFolder;

  @Bean
  public AmazonS3 s3client() {
    BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsKeyId, awsSecretKey);
    return AmazonS3ClientBuilder.standard()
        .withRegion(Regions.fromName(region))
        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
        .build();
  }
}