package com.collection.pokergame.enums;

public enum PokerSuitEnum {
	DIAMOND(1, "方块"),
	CLUB(2, "梅花"),
	HEART(3, "红桃"),
	SPADE(4, "黑桃");
	
	private Integer suitPriority;

    private String suitName;
    
	private PokerSuitEnum(Integer suitPriority, String suitName) {
		this.suitPriority = suitPriority;
		this.suitName = suitName;
	}

	public Integer getSuitPriority() {
		return suitPriority;
	}

	public void setSuitPriority(Integer suitPriority) {
		this.suitPriority = suitPriority;
	}

	public String getSuitName() {
		return suitName;
	}

	public void setSuitName(String suitName) {
		this.suitName = suitName;
	}

	public static PokerSuitEnum priorityOf(String suitName){
		for (PokerSuitEnum suit : values()) {
			if(suit.equals(suit.getSuitName())) {
				return suit;
			}
		}
		return null;
	}

}
