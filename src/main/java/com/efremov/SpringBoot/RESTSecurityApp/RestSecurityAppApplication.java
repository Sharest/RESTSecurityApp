package com.efremov.SpringBoot.RESTSecurityApp;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSecurityAppApplication.class, args);}
}
