package com.cos.util;

import java.security.MessageDigest;

//256bit 길이의 암호 = 해시 = 복호화가 안됨.
public class SHA256 {
	// password를 암호화 해서 return
	
	
	public static String getEncypt(String rawPassword, String salt ) {
		
		//rawPassword = "qw5678qw;"
		String result = "";
		
		//[q, w, 5, 6, 7, 8, q, w]
		byte[] b = (rawPassword+salt).getBytes();
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b); // pw+salt MessageDigest가 SHA-256으로 암호화된 값을 들고 있음.
			byte[] bresult = md.digest();
			
			for(byte data : bresult) {
				System.out.print(data+" ");
			}
			System.out.println();
			
			StringBuffer sb =new StringBuffer();
			for (byte data : bresult) {
				sb.append(Integer.toString(data & 0xff, 16));
			}
			result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		getEncypt("qw5678qw", "cos");
	}
}
