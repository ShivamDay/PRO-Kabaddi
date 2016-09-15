package com.prokabaddi.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prokabaddi.entity.MatchSchedule;
import com.prokabaddi.entity.Team;
import com.prokabaddi.services.KabaddiServices;

/**
 * @author Shivam
 * 
 */

@Controller
@RequestMapping("scheduler")
public class RestController {

	@Autowired
	KabaddiServices kabaddiService;

	static final Logger logger = Logger.getLogger(RestController.class);

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<MatchSchedule> createSchedule(@RequestBody List<Team> teamLists) throws Exception {
			List<MatchSchedule> matcheScheduleList = kabaddiService.createSchedule(teamLists);
			return matcheScheduleList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/team", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Team> addTeam(@RequestBody List<Team> teamLists) throws Exception {
			return kabaddiService.addTeams(teamLists);
	}
	
	@ResponseBody
	@RequestMapping(value = "allteam", method = RequestMethod.GET )
	public List<Team> getTeam(@RequestBody List<Team> teamLists) throws Exception {
			return kabaddiService.getAllTeams();
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "allschedule", method = RequestMethod.GET)
	public List<MatchSchedule> getAllSchedule() throws Exception {
		return kabaddiService.getAllSchedules();
	}
	
	
}
