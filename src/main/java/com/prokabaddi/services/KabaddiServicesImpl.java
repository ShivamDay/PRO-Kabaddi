package com.prokabaddi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prokabaddi.entity.Match;
import com.prokabaddi.entity.MatchSchedule;
import com.prokabaddi.entity.Team;
import com.prokabaddi.myscheduler.ScheduleUtil;
import com.prokabaddi.repository.MatchRepository;
import com.prokabaddi.repository.TeamRepository;

/**
 * @author Shivam
 * 
 */

@Component
public class KabaddiServicesImpl implements KabaddiServices {

	@Autowired
	private ScheduleUtil scheduleUtil;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Override
	public List<Team> addTeams(List<Team> teamList) throws Exception {
		return teamRepository.save(teamList);
	}

	@Override
	public List<MatchSchedule> createSchedule(List<Team> teamList)
			throws Exception {
		validateList(teamList);
		
		addTeams(teamList);
		
		List<Match> matchList = new ArrayList<Match>();
		scheduleUtil.matchTeams(teamList, matchList, 0);
		
		List<MatchSchedule> scheduleMatches = scheduleUtil.scheduleMatches(matchList);
		
		saveMatchList(scheduleMatches);
		
		return scheduleMatches;
	}

	private void validateList(List<Team> teamList) throws Exception {
		if (null == teamList || teamList.isEmpty()) {
			throw new Exception("Invalid team list");
		}
	}

	@Override
	public List<MatchSchedule> getAllSchedules() throws Exception {
		List<Match> allMatches = matchRepository.findAll();
		return scheduleUtil.convertToMatchSchedule(allMatches);
	}

	@Override
	public List<Team> getAllTeams() throws Exception {
		return teamRepository.findAll();
	}

	@Override
	public Match saveMatch(Match match) {
		return matchRepository.save(match);
	}
	
	private void saveMatchList(List<MatchSchedule> scheduleMatches)
	{
		List<Match> matchList = new ArrayList<>();
		for(MatchSchedule ms: scheduleMatches)
		{
			matchList.addAll(ms.getMatchList());
		}
		matchRepository.save(matchList);
	}
	
}
