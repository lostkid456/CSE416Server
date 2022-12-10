package com.server.server;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.server.server.config.DBConfigProperties;
import com.server.server.config.JPAConfigProperties;
import com.server.server.model.*;
import com.server.server.model.enums.InterestType;
import com.server.server.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({DBConfigProperties.class, JPAConfigProperties.class})
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		try {
			File dir = new File(ResourceUtils.getFile("classpath:geoJson/NorthCarolina").getAbsolutePath());
			File[] directoryListing=dir.listFiles();
			for(File file:directoryListing){
				System.out.println(file.getName());
			}
//			System.out.println(ResourceUtils.toURI("geoJson/"));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
