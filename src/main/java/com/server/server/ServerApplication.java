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
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({DBConfigProperties.class, JPAConfigProperties.class})
public class ServerApplication {
	@Autowired
	private static StateRepository stateRepository;
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		addStateData("Ohio");
		addStateData("North Carolina");
		addStateData("Virginia");
	}

	public static void addStateData(String state) {
		State newState = new State();

		List<Ensemble> ensembles = new ArrayList<>();
		Ensemble smd = new Ensemble();
		Ensemble mmd = new Ensemble();

		List<StateDemographic> stateDemographics = new ArrayList<>();

		List<DistrictPlan> smdDistrictPlans=new ArrayList<>();
		List<BoxAndWhisker> smdBoxAndWhiskers=new ArrayList<>();
		List<RepDemSplit> smdRepDemSplits=new ArrayList<>();

		DistrictPlan enactedPlan=new DistrictPlan();
		enactedPlan.setPattern("smd");
		enactedPlan.setSplit("");
		enactedPlan.setPlanType("enacted");

		List<DistrictPlan> mmdDistrictPlans=new ArrayList<>();
		List<BoxAndWhisker> mmdBoxAndWhiskers=new ArrayList<>();
		List<RepDemSplit> mmdRepDemSplits=new ArrayList<>();

		CSVReader csvReader;
		try {
			switch (state) {
				case "Ohio":
					csvReader=new CSVReader(new FileReader(ResourceUtils.getFile("classpath:enacted/Ohio" +
																						 "/Ohio_Enacted_Demographics" +
																						 ".csv")));
					csvReader.skip(1);
					String[] line;
					int total,caucasian,asian,african,latino,republican,democrat,majorityMinority;
					total=0;
					caucasian=0;
					asian=0;
					african=0;
					latino=0;
					republican=0;
					democrat=0;
					majorityMinority=0;
					while((line=csvReader.readNext())!=null){
						System.out.println(line[1]);
					}
					newState.setState("OH");
					break;
				case "Virginia":
					newState.setState("VA");
					break;
				case "North Carolina":
					newState.setState("NC");
					break;
				default:
					break;
			}

//			ensembles.add(smd);
//			ensembles.add(mmd);
//			newState.setEnsembles(ensembles);
//			newState.setStateDemographics(stateDemographics);
//			stateRepository.save(newState);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
