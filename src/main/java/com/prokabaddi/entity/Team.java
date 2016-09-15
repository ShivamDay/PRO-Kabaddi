package com.prokabaddi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Shivam
 * 
 */

@Entity
@Table(name = "team")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1229079684923091669L;

	@Id
	@GeneratedValue
	@Column(name = "team_id")
	private long id;

	@Column(name = "team_name")
	private String teamName;

	@Column(name = "city")
	private String city;

	public Team() {
	}

	public Team(String name, String city) {
		this.teamName = name;
		this.city = city;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return teamName;
	}

}