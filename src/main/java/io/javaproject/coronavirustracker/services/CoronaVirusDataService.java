package io.javaproject.coronavirustracker.services;
import java.util.*;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.HttpResource;

import io.javaproject.coronavirustracker.model.LocationStats;



@Service
public class CoronaVirusDataService {
	
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	
	private List<LocationStats> allStats = new ArrayList<>();
	
	
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}



	@PostConstruct
	@Scheduled(cron = "* * 1  * * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{
		List<LocationStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()  //We are creating the request with the target URL
				       .uri(URI.create(VIRUS_DATA_URL))
				       .build();
		HttpResponse<String> httpResponse =     //We are sending the request and storing the response in the string format
		     client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader reader = new StringReader(httpResponse.body());  //Converting the string to reader
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		for (CSVRecord record : records)
		
		{
			
			LocationStats stats= new LocationStats();
			stats.setState(record.get("Province/State"));
		    stats.setCountry(record.get("Country/Region"));
		    int latestCases = Integer.parseInt(record.get(record.size() - 1));
		    int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
		    stats.setLastestTotalCases(latestCases);
		    stats.setDiffFromPrevDay(latestCases - prevDayCases);
		    newStats.add(stats);
		    System.out.println(stats);
		    
        }
		
		this.allStats = newStats;

    }
}
