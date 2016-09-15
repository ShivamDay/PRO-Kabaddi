package com.prokabaddi.services;

import java.util.List;

import com.prokabaddi.entity.Match;
import com.prokabaddi.entity.MatchSchedule;
import com.prokabaddi.entity.Team;

/**
 * @author Shivam
 * 
 */

public interface KabaddiServices {
	List<MatchSchedule> createSchedule(List<Team> teamLists) throws Exception;
	List<MatchSchedule> getAllSchedules() throws Exception;
	List<Team> addTeams(List<Team> teamList) throws Exception;
	List<Team> getAllTeams() throws Exception;
	Match saveMatch(Match match);
	
}
