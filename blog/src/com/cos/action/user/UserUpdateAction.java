package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//목적 : form 태그에 있는 name 값을 받아서 DB에 insert하고 나서 페이지 이동
		
		//null 값 처리하기 (나중에), 유효성 검사
			int id = Integer.parseInt(request.getParameter("id"));
			String username = request.getParameter("username");
			String rawPassword = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");	//주소추가
			String password = SHA256.getEncypt(rawPassword, "cos");

			
			User user = new User();
			user.setId(id);
			user.setUsername(username);
			user.setPassword(password); //Encryption(암호화해야함)
			user.setEmail(email);
			user.setAddress(address); //주소추가
			
			//DAO 연결하기
			UserDao dao = new UserDao();
			int result = dao.update(user);
			
			if(result ==1) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("/blog/board/index.jsp");
			}else {
				Script.back(response);
			}
			 
			
	}

}
