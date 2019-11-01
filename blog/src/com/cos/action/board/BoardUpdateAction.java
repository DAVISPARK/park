package com.cos.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.model.User;
import com.cos.util.Script;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. 3건을 board 객체로 만들어서 넘김 
		Board board = new Board();
		board.setId(id);
		board.setTitle(title);
		board.setContent(content);
		
		// 3.  DAO 접근 -> 업데이트 수행 update(board)
		BoardDao dao = new BoardDao();
		int result = dao.update(board);
		
		//4. 정상이면 -> detail 서블릿 호출, 비정상이면 -> 뒤로가기
		if(result ==1) {
			response.sendRedirect("/blog/board?cmd=detail&id="+id);
		}else {
			Script.back(response);
		}
		
	}
	}

	
	


