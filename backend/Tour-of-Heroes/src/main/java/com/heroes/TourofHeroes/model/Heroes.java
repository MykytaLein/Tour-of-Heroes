package com.heroes.TourofHeroes.model;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "heroes")
public class Heroes {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "heroes_id", strategy = "com.heroes.TourofHeroes.generator.HeroesIDGenerator")
    @GeneratedValue(generator = "heroes_id")  
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "strength")
	private int strength;

	public Heroes(String name, int strength) {
		super();
		this.name = name;
		this.strength = strength;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public Heroes() {

	}

}
