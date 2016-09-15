package com.prokabaddi.test.myscheduler;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.prokabaddi.entity.MatchSchedule;
import com.prokabaddi.entity.Team;
import com.prokabaddi.myscheduler.ScheduleUtil;

/**
 * @author Shivam
 * 
 */


public class ChampionshipTest extends TestCase{
	public static ArrayList<Team> teamList = new ArrayList<Team>();
	public static ScheduleUtil schedule = ScheduleUtil.getInstance();

	public ChampionshipTest() {
	}

	public void createProKabaddiLeagueList() {
		teamList.add(new Team("Pink Panthers", "Jaipur"));
		teamList.add(new Team("U Mumbai Team", "Mumbai"));
		teamList.add(new Team("Bengaluru Bulls Team ", "Bengaluru"));
		teamList.add(new Team("Patna Pirates Team", "Patna"));
		teamList.add(new Team("Telugu Titans Team", "Telugu"));
		teamList.add(new Team("Dabang Delhi team", "Delhi"));
		teamList.add(new Team("Bengal Warriors team", "Bengal"));
		teamList.add(new Team("Puneri Paltan team ", "Pune"));
	}

	public void createTLeagueList() {
		teamList.add(new Team("T1", "1"));
		teamList.add(new Team("T2", "2"));
		teamList.add(new Team("T3", "3"));
		teamList.add(new Team("T4", "4"));
		//teamList.add(new Team("T5", "5"));
		//teamList.add(new Team("T6", "6"));
		// teamList.add(new Team("T7", "4"));

	}

	@Test
	public void testScheduler()
	{
		createTLeagueList();
		List<MatchSchedule> matchSchedule = schedule.getSchedule(teamList);
		assertNotNull(matchSchedule);
	}
}