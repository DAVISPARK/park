package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.model.Board;
import com.cos.util.DBClose;

public class BoardDao {
	private Connection conn; // MySQL과 연결하기 위해 필요
	private PreparedStatement pstmt; // 쿼리문을 작성 - 실행하기 위해 필요
	private ResultSet rs; // 결과를 보관할 커서
	
	//인기리스트 3건 가져오기
	public List<Board> findOrderByReadCountDesc() {
		final String SQL = "SELECT * FROM board ORDER BY readCount DESC limit 3";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<>();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next() 커서 이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));

				boards.add(board); // 컬렉션에 담아주기
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	

	public int increaseReadCount(int id) {
		final String SQL = "UPDATE board SET readCount = readCount + 1 WHERE id =?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);

			int result = pstmt.executeUpdate(); // 변경된 튜플의 개수를 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	

	public int save(Board board) {
		final String SQL = "insert into board(userId, title, content, createDate) values(?,?,?,now())";

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			int result = pstmt.executeUpdate(); // 변경된 튜플의 개수를 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	// 리스트보기 SELCET * FROM board ORDER BY id DESC
	public List<Board> findAll() {
		final String SQL = "SELECT * FROM board ORDER BY id DESC";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<>();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next() 커서 이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));

				boards.add(board); // 컬렉션에 담아주기
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<Board> findAll(int page) {
		final String SQL = "select * from board b , user u where b.userId = u.id order by b.id desc limit ?, 3";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<>();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, (page-1)*3);
			rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next() 커서 이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content")+" "); // + " " <- 유투브 미리보기 오류로 방지철
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				//board에 user 객체에 username 저장
				board.getUser().setUsername(rs.getString("u.username"));
				

				boards.add(board); // 컬렉션에 담아주기
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}


	// 상세보기 SELECT * FROM board WHERE id = ?
	public Board findById(int id) {
		final String SQL = "select * from board b , user u where b.userId = u.id and b.id =?";
		conn = DBConn.getConnection();

		try {

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) { // rs.next() 커서 이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				
				board.getUser().setUsername(rs.getString("u.username"));

				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}

	public int delete(int id) { //가져오는게 없음 void
		final String SQL = "DELETE FROM board WHERE id =?";
		conn = DBConn.getConnection();
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return -1;
	}


	public int update(Board board) {
	final String SQL = "UPDATE board SET title=?, content=? WHERE id=?" ;
		
		conn= DBConn.getConnection();
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return -1;
	}
	


}
