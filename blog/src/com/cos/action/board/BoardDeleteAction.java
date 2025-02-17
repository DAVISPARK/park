package com.cos.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.util.Script;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			return; // 리턴 값이 없으면 리턴가능
		}

		int id = Integer.parseInt(request.getParameter("id"));
		// DAO 접근
		BoardDao dao = new BoardDao();
		int result = dao.delete(id);

		// list 서블릿 호출
		if (result == 1) {
			response.sendRedirect("/blog/board/index.jsp");
		} else {
			Script.back(response);
		}
	}

}
