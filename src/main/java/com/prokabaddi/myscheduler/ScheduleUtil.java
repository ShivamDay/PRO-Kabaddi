package com.prokabaddi.myscheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.prokabaddi.entity.Match;
import com.prokabaddi.entity.MatchSchedule;
import com.prokabaddi.entity.Team;

/**
 * @author Shivam
 * 
 */

@Component
public class ScheduleUtil {

	private static ScheduleUtil self = new ScheduleUtil();
	
	private ScheduleUtil() {
		
	}

	public static ScheduleUtil getInstance()
	{
		return self;	
	}
	
	public List<MatchSchedule> getSchedule(List<Team> teamList) {

		List<Match> matchList = new ArrayList<>();
		matchTeams(teamList, matchList, 0);

		Map<Date, List<Match>> schedule2 = schedule(matchList, new Date());

		Map<Date, List<Match>> treeMap = new TreeMap<Date, List<Match>>(
				schedule2);

		List<MatchSchedule> matcheScheduleList = mapToMatchesList(treeMap);

		return matcheScheduleList;
	}

	public List<MatchSchedule> scheduleMatches(List<Match> matchList) {

		Map<Date, List<Match>> scheduleMatchMap = schedule(matchList,
				new Date());

		Map<Date, List<Match>> treeMap = new TreeMap<Date, List<Match>>(
				scheduleMatchMap);

		List<MatchSchedule> matcheScheduleList = mapToMatchesList(treeMap);

		return matcheScheduleList;
	}

	private Map<Date, List<Match>> schedule(List<Match> schduleMatchList,
			Date startDate) {
		Date previousDate = startDate;
		Map<Date, List<Match>> dateMatchMap = new HashMap<>();
		while (!schduleMatchList.isEmpty()) {
			for (int i = 0; i < schduleMatchList.size(); i++) {
				Match match = schduleMatchList.get(i);
				if (dateMatchMap.isEmpty()) {
					addMatchTOMap(schduleMatchList, startDate, dateMatchMap,
							match);
				} else {
					List<Match> preList = dateMatchMap.get(previousDate);
					if (preList == null || preList.isEmpty()) {
						addMatchTOMap(schduleMatchList, startDate,
								dateMatchMap, match);
						previousDate = startDate;
					} else if (preList.size() < 2) {
						if (!exists(preList, match)) {
							match.setMatchDate(previousDate);
							preList.add(match);
							schduleMatchList.remove(match);
						}
					} else {
						startDate = nextDate(previousDate);
						if (!exists(preList, match)) {
							addMatchTOMap(schduleMatchList, startDate,
									dateMatchMap, match);
							previousDate = startDate;
						}
					}
				}
			}
			previousDate = startDate;
			startDate = nextDate(previousDate);
		}

		return dateMatchMap;
	}

	private void addMatchTOMap(List<Match> schduleMatchList, Date matchDate,
			Map<Date, List<Match>> dateMatchMap, Match match) {
		List<Match> matchList = new ArrayList<>();
		match.setMatchDate(matchDate);
		matchList.add(match);
		dateMatchMap.put(matchDate, matchList);
		schduleMatchList.remove(match);
	}

	private Date nextDate(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	private boolean exists(List<Match> gameList, Match game) {
		boolean isSameTeam = false;
		for (Match g : gameList) {
			isSameTeam = sameTeam(g, game);
			if (isSameTeam) {
				return true;
			}
		}
		return false;
	}

	private boolean sameTeam(Match game1, Match game2) {
		if (game1.getTeamTwo().getTeamName()
				.equals(game2.getTeamTwo().getTeamName())
				|| game1.getTeamTwo().getTeamName()
						.equals(game2.getTeamOne().getTeamName())
				|| game1.getTeamOne().getTeamName()
						.equals(game2.getTeamTwo().getTeamName())
				|| game1.getTeamOne().getTeamName()
						.equals(game2.getTeamOne().getTeamName())) {
			return true;
		}
		return false;
	}

	private List<MatchSchedule> mapToMatchesList(
			Map<Date, List<Match>> matchSchedule) {
		List<MatchSchedule> matchesList = new ArrayList<>();
		for (Entry<Date, List<Match>> ent : matchSchedule.entrySet()) {
			MatchSchedule matches = new MatchSchedule();
			matches.setMatchDate(ent.getKey());
			matches.setMatchList(ent.getValue());
			matchesList.add(matches);
		}
		return matchesList;
	}

	public void matchTeams(List<Team> list, List<Match> schduleGameList, int j) {
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {

			if ((listSize - (i + 1)) > j) {
				Match matchHome = new Match();
				matchHome.setTeamOne(list.get(j));
				matchHome.setTeamTwo(list.get(listSize - (i + 1)));
				matchHome.setMatchLocation(matchHome.getTeamOne().getCity());
				schduleGameList.add(matchHome);

				Match matchAway = new Match();
				matchAway.setTeamOne(list.get(j));
				matchAway.setTeamTwo(list.get(listSize - (i + 1)));
				matchAway.setMatchLocation(matchAway.getTeamTwo().getCity());
				schduleGameList.add(matchAway);
			}
		}

		if (j + 1 < list.size()) {
			matchTeams(list, schduleGameList, j + 1);
		}
	}

	public List<MatchSchedule> convertToMatchSchedule(List<Match> allMatches) {
		Map<Date, MatchSchedule> matchMap = new TreeMap<Date, MatchSchedule>();
		for (Match m : allMatches) {
			if (matchMap.containsKey(m.getMatchDate())) {
				MatchSchedule matchSchedule = matchMap.get(m.getMatchDate());
				matchSchedule.getMatchList().add(m);
				matchSchedule.setMatchDate(m.getMatchDate());
				matchMap.put(m.getMatchDate(), matchSchedule);
			} else {
				MatchSchedule matchSchedule = new MatchSchedule();
				List<Match> mList = new ArrayList<>();
				mList.add(m);
				matchSchedule.setMatchList(mList);
				matchSchedule.setMatchDate(m.getMatchDate());
				matchMap.put(m.getMatchDate(), matchSchedule);
			}
		}
		
		List<MatchSchedule> mList = new ArrayList<>(matchMap.values());
		return mList;
	}

}