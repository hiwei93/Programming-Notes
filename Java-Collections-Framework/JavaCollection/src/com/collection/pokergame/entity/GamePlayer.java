package com.collection.pokergame.entity;

import java.util.Collections;
import java.util.List;

public class GamePlayer implements Comparable<GamePlayer>{

	private int id;
	
	private String name;
	
	private List<Poker> pokers;

	public GamePlayer(int id, String name, List<Poker> pokers) {
		super();
		this.id = id;
		this.name = name;
		this.pokers = pokers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Poker> getPokers() {
		return pokers;
	}

	public void setPokers(List<Poker> pokers) {
		this.pokers = pokers;
	}

	@Override
	public int compareTo(GamePlayer o) {
		return Collections.max(this.getPokers()).compareTo(Collections.max(o.getPokers()));
	}
	
}
