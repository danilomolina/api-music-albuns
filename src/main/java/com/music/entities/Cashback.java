package com.music.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity cashback, armazena os percentuais de cada genero por dia da semana
 * @author danilo
 *
 */
@Entity
@Table(name = "cashback")
public class Cashback implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String genre;
	private int percSunday;
	private int percMonday;
	private int percTuesday;
	private int percWednesday;
	private int percThursday;
	private int percFriday;
	private int percSaturday;	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getPercSunday() {
		return percSunday;
	}
	public void setPercSunday(int percSunday) {
		this.percSunday = percSunday;
	}
	public int getPercMonday() {
		return percMonday;
	}
	public void setPercMonday(int percMonday) {
		this.percMonday = percMonday;
	}
	public int getPercTuesday() {
		return percTuesday;
	}
	public void setPercTuesday(int percTuesday) {
		this.percTuesday = percTuesday;
	}
	public int getPercWednesday() {
		return percWednesday;
	}
	public void setPercWednesday(int percWednesday) {
		this.percWednesday = percWednesday;
	}
	public int getPercThursday() {
		return percThursday;
	}
	public void setPercThursday(int percThursday) {
		this.percThursday = percThursday;
	}
	public int getPercFriday() {
		return percFriday;
	}
	public void setPercFriday(int percFriday) {
		this.percFriday = percFriday;
	}
	public int getPercSaturday() {
		return percSaturday;
	}
	public void setPercSaturday(int percSaturday) {
		this.percSaturday = percSaturday;
	}
	
	@Override
	public String toString() {
		return "Cashback [id=" + id + ", genre=" + genre + ", percSunday=" + percSunday + ", percMonday=" + percMonday
				+ ", percTuesday=" + percTuesday + ", percWednesday=" + percWednesday + ", percThursday=" + percThursday
				+ ", percFriday=" + percFriday + ", percSaturday=" + percSaturday + "]";
	}
	
	
	
	

}
