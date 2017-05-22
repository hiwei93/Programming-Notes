package com.collection.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.collection.pokergame.entity.Dealer;
import com.collection.pokergame.entity.GamePlayer;
import com.collection.pokergame.entity.Poker;

public class TestPokerGame {

	public static void main(String[] args) {
		new TestPokerGame().multiPlayer(3);		
	}
	
	// 自定义玩家数量
	public void multiPlayer(int num){
		
		String name;
		List<GamePlayer> gamePlayers = new ArrayList<GamePlayer>(num);
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("******************这是" + num + "人游戏，请玩家入座****************");
		
		for(int i = 1; i <= num; i++){
			System.out.println("**************玩家" + num + "请输入名字：*****************");
			System.out.println("请输入用户名：");
			name = input.next();
			List<Poker> playerPokers = new ArrayList<Poker>(2);
			GamePlayer player = new GamePlayer(i, name, playerPokers);	
			gamePlayers.add(player);
		}
		
		System.out.println("*************等待发牌机发牌****************");
		List<Poker> pokers = Dealer.getADeckOfPoker();
		Collections.shuffle(pokers);
		for (Poker poker : pokers) {
			System.out.println(poker);
		}
		
//		for (int i = 1; i <= 2 * num; i++) {
//			if(i % num == 0) {
//				gamePlayers.get(num -1).getPokers().add(Dealer.deal(pokers));
//			} else {
//				for (int j = 1; j < num; j++) {
//					if(i % num == j) {
//						gamePlayers.get(j - 1).getPokers().add(Dealer.deal(pokers));
//					}
//				}
//			}
//		}
//		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < num; j++) {
				gamePlayers.get(j).getPokers().add(Dealer.deal(pokers));
			}
		}
		
		for (int i = 0; i < gamePlayers.size(); i++) {
			GamePlayer player = gamePlayers.get(i);
			System.out.println("玩家" + player.getId() + "：" + player.getName()  + "最大的牌是：" + Collections.max(player.getPokers()));
		}
		
		GamePlayer winner = Collections.max(gamePlayers);
		System.out.println("获胜者是：玩家" + winner.getId() + "-" + winner.getName());
		
	}
	
	public void twoPlayers(){
		int id;
		String name;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("**************请注册用户1*****************");
		System.out.println("请输入用户ID：");
		id = Integer.valueOf(input.next());
		System.out.println("请输入用户名：");
		name = input.next();
		List<Poker> player1pokers = new ArrayList<Poker>(2);
		GamePlayer player1 = new GamePlayer(id, name, player1pokers);
		
		System.out.println("**************请注册用户2*****************");
		System.out.println("请输入用户ID：");
		id = Integer.valueOf(input.next());
		System.out.println("请输入用户名：");
		name = input.next();
		List<Poker> player2pokers = new ArrayList<Poker>(2);
		GamePlayer player2 = new GamePlayer(id, name, player2pokers);
		
		System.out.println("*************等待发牌机发牌****************");
		List<Poker> pokers = Dealer.getADeckOfPoker();
		Collections.shuffle(pokers);
		for (Poker poker : pokers) {
			System.out.println(poker);
		}
		
		player1.getPokers().add(Dealer.deal(pokers));
		player2.getPokers().add(Dealer.deal(pokers));
		player1.getPokers().add(Dealer.deal(pokers));
		player2.getPokers().add(Dealer.deal(pokers));
		
		System.out.println("玩儿家1" + player1.getName() + "最大的牌是：" + Collections.max(player1.getPokers()));
		System.out.println("玩儿家2" + player2.getName()  +"最大的牌是：" + Collections.max(player2.getPokers()));
		
		int compareResult = player1.compareTo(player2);
		switch (compareResult) {
		case 1:
			System.out.println("玩儿家1" + player1.getName() + "胜！");
			break;
		case -1:
			System.out.println("玩儿家2" + player2.getName() + "胜！");
			break;
		case 0:
			System.out.println("平局！");
			break;
		}
	}
}
