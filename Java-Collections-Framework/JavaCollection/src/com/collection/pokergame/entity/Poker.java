package com.collection.pokergame.entity;

import com.collection.pokergame.enums.PokerPointEnum;
import com.collection.pokergame.enums.PokerSuitEnum;


public class Poker implements Comparable<Poker>{
	
	private PokerSuitEnum suit; // 花色
	
	private PokerPointEnum point; // 点数	
	
	public Poker(PokerSuitEnum suit, PokerPointEnum point) {
		super();
		this.suit = suit;
		this.point = point;
	}

	@Override
	public int compareTo(Poker o) {
		int result = this.point.getPointPriority().compareTo(o.point.getPointPriority());
		if (result == 0){
			result = this.suit.getSuitPriority().compareTo(o.suit.getSuitPriority()); 
		}
		return result;
	}

	@Override
	public String toString() {
		return "Poker [suit=" + suit.getSuitName() + ", point=" + point.getPointName() + "]";
	}
	
}
