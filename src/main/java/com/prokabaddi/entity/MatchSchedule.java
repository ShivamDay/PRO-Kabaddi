/**
 * 
 */
package com.prokabaddi.entity;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prokabaddi.myscheduler.DateTimeSerializer;

/**
 * @author Shivam
 * 
 */

public class MatchSchedule {

	Date matchDate;
	List<Match> matchList;

	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	
	public List<Match> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}
}
