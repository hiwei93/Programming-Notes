package com.collection.pokergame.enums;

public enum CompareStateEnum {
	GT(1, "大于"),
	ET(0, "等于"),
	LT(-1, "小于");
	
	private int state;
	
	private String stateInfo;

	private CompareStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public static CompareStateEnum stateOf(int result) {
		for (CompareStateEnum state : values()) {
			if (state.getState() == result) {
				return state;
			}
		}
		return null;
	}

}
