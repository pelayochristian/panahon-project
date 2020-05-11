package com.project.panahon.hystrix_dashboard_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardServiceApplication.class, args);
	}

}
