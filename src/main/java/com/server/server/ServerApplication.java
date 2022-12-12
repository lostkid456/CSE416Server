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
import java.util.*;

@SpringBootApplication
@EnableConfigurationProperties({DBConfigProperties.class, JPAConfigProperties.class})
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
//		try {
//			File mmd = new File(ResourceUtils.getFile("classpath:mmd/NorthCarolina").getAbsolutePath());
//			File[] dirListing=mmd.listFiles();
//			for(File file:dirListing){
//				System.out.println(file.getName());
//				File[] mmdGroup=file.listFiles();
//				for(File mmdG:mmdGroup){
//					System.out.println(mmdG.getName());
//				}
//			}
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}
	}
}
