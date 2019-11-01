package com.cos.util;

public class Test {
	
	public static void main(String[] args) {
		byte b = 117; 	//	2의 8승 = 256 
		
		//byte를 String으로 
		String s = Integer.toString(b);
		System.out.println(s);
		
		//Test 비트 연산
		// 00000000 00000000 00000000 00000000 = binary
		int i = 128;
		System.out.println(Integer.toBinaryString(i));
		
		// 00000000 00000000 00000000 10010110 = int
		// 11111111 11111111 11111111 10010110 = binary
		// 00000000 00000000 00000000 11111111 = 0xff
		int j = 150;
		System.out.println(Integer.toBinaryString(j));		//150 = 128 + 16 + 4 + 2
		System.out.println(Integer.toString((byte)j & 0xff, 16));
		
	}

}
