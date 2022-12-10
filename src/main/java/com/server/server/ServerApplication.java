package com.server.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties({DBConfigProperties.class, JPAConfigProperties.class})
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		try {
			File dir = new File(ResourceUtils.getFile("classpath:smd/geoJson/NorthCarolina").getAbsolutePath());
			File[] directoryListing=dir.listFiles();
			assert directoryListing != null;
			for(File file:directoryListing){
				String name= file.getName();
				ObjectMapper objectMapper=new ObjectMapper();
				Map<String,Object> obj=objectMapper.readValue(file, new TypeReference<>() {
				});
				System.out.println(obj.get("features"));
			}
//			System.out.println(ResourceUtils.toURI("geoJson/"));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
