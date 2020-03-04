package io.javaproject.coronavirustracker.controllers;
import io.javaproject.coronavirustracker.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.javaproject.coronavirustracker.services.CoronaVirusDataService;


@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@RequestMapping("/")
	public String home(Model model)
	{  
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLastestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats" , allStats);
		model.addAttribute("totalReportedCases" , totalCases);
		model.addAttribute("totalNewCases" , totalNewCases);
		return "home";
	}
}
