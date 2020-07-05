package com.rmsMagement.RMSManagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class RmsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmsManagementApplication.class, args);
	}

}
