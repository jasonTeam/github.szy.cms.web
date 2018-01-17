package com.cms.szy.inner;

/**
 * 
 *【用于测试内部类】  
 * @author ShenZiYang 
 * @date 2018年1月17日 下午12:37:28
 */
public class Test {
	
	public void test(Bird bird){
		System.out.println(bird.getBirdName() + "能够飞 " + bird.fly() + "米");  
	}
	
	public static void main(String[] args){
		Test test = new Test();
		//内部类
		test.test(new Bird(){
			@Override
			public int fly() {
				return 10000;
			}
			
			public String getBirdName(){
				return "大雁";
			}
		});
	}
	
	
	
	
}
