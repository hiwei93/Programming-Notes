package com.collection.pokergame.enums;

public enum PokerPointEnum {
	ACE(13, "A"),
	TOW(1, "2"),
	THREE(2, "3"),
	FOUR(3, "4"),
	FIVE(4, "5"),
	SIX(5, "6"),
	SEVEN(6, "7"),
	EIGHT(7, "8"),
	NINE(8, "9"),
	TEN(9, "10"),
	JACK(10, "J"),
	QUEEN(11, "Q"),
	KING(12, "K");
	
	private Integer pointPriority;
	
	private String pointName;

	private PokerPointEnum(Integer pointPriority, String pointName) {
		this.pointPriority = pointPriority;
		this.pointName = pointName;
	}

	public Integer getPointPriority() {
		return pointPriority;
	}

	public void setPointPriority(Integer pointPriority) {
		this.pointPriority = pointPriority;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	
}
