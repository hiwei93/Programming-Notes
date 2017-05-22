package com.collection.pokergame.entity;

import java.util.ArrayList;
import java.util.List;

import com.collection.pokergame.enums.PokerPointEnum;
import com.collection.pokergame.enums.PokerSuitEnum;

// 发牌器
public class Dealer {

	// 获取一副扑克
	public static List<Poker> getADeckOfPoker() {
		List<Poker> pokers = new ArrayList<Poker>();
		
		for (PokerPointEnum pockerPoint : PokerPointEnum.values()) {
			for (PokerSuitEnum pokerSuit : PokerSuitEnum.values()) {
				pokers.add(new Poker(pokerSuit, pockerPoint));
			}
		}
		
		return pokers;
	}
	
	// 发牌
	public static Poker deal(List<Poker> pokers){
		return pokers.remove(1);
	}
	
}
