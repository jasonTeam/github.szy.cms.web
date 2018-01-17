package com.cms.szy.inner;

/**
 * 
 *【用于测试内部类】  
 * @author ShenZiYang 
 * @date 2018年1月17日 下午12:37:02
 */
public abstract class Bird {
	private String birdName;
	
	//抽象方法
	public abstract int fly();
	
	public String getBirdName() {
		return birdName;
	}

	public void setBirdName(String birdName) {
		this.birdName = birdName;
	}
	
	
}
