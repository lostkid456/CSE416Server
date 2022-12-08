package com.server.server;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.server.server.config.DBConfigProperties;
import com.server.server.config.JPAConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({DBConfigProperties.class, JPAConfigProperties.class})
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		try{
			FileReader fileReader=new FileReader(ResourceUtils.getFile("classpath:data/Ohio/Ohio_Enacted_Demographics.csv"));
			CSVReader csvReader=new CSVReaderBuilder(fileReader).withSkipLines(1).build();
			List<String[]> allData=csvReader.readAll();
			for(String[] row:allData){
				for(String cell:row){
					System.out.println(cell);
				}
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
