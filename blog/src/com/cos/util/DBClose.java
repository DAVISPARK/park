package com.cos.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * DML(INSERT, UPDATE, DELETE) CLOSE
 * 
 * */

public class DBClose {
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	}
	/*
	 * DQL(SELECT) CLOSE
	 * 
	 * */


		public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
		}

}
