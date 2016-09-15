package com.prokabaddi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prokabaddi.myscheduler.DateTimeSerializer;

/**
 * @author Shivam
 * 
 */

@Entity
@Table(name = "k_match")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Match implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 597799584438811246L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@OneToOne
	@JoinColumn(name = "team_one_id", referencedColumnName = "team_id", nullable = false, updatable = false)
	private Team teamOne;

	@OneToOne
	@JoinColumn(name = "team_two_id", referencedColumnName = "team_id", nullable = false, updatable = false)
	private Team teamTwo;

	@Column(name = "match_location")
	private String matchLocation;

	@Column(name = "match_date")
	private Date matchDate;

	public Match() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Team getTeamOne() {
		return teamOne;
	}

	public void setTeamOne(Team teamHome) {
		this.teamOne = teamHome;
	}

	public Team getTeamTwo() {
		return teamTwo;
	}

	public void setTeamTwo(Team teamAway) {
		this.teamTwo = teamAway;
	}

	public String getMatchLocation() {
		return matchLocation;
	}

	public void setMatchLocation(String matchLocation) {
		this.matchLocation = matchLocation;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	@Override
	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(teamOne.getTeamName()).append(" Vs ")
				.append(teamTwo.getTeamName()).append(" at ")
				.append(matchLocation);
		return strBuild.toString();
	}

}