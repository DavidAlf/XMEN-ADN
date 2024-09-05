package com.projects.xmen_adn.infrastructure.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.projects.xmen_adn.infrastructure.adapter.repository")
public class DynamoConfig {

    @Value("${awsEndpoint}")
    private String endpoint;

    @Value("${awsRegion}")
    private String region;

    @Value("${awsAccesskey}")
    private String accessKey;

    @Value("${awsSecretkey}")
    private String secreKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(awsCredentialsProvider()).build();
        return amazonDynamoDB;

    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secreKey));
    }

    @PostConstruct
    public void init() {
        System.out.println("DynamoDB Endpoint: " + endpoint);
        System.out.println("AWS Region: " + region);
        System.out.println("AWS Access Key: " + accessKey);
        System.out.println("AWS Secret Key: " + secreKey);
    }
}