package com.cms.szy.demo;

import java.math.BigDecimal;

public class BigDecimalTest {
	
	public static void main(String[] args){
		
		BigDecimal bigDeci = new BigDecimal(98);
		
		BigDecimal bigDecis = bigDeci.multiply(new BigDecimal(1.05));
		
		System.out.println(bigDecis.intValue());
		
	}
	
}
