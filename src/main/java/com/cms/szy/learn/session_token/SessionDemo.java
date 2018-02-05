package com.cms.szy.learn.session_token;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 *【session和cookie例子】  
 * @author ShenZiYang 
 * @date 2018年2月5日 上午10:01:25
 */
public class SessionDemo extends HttpServlet{

	private static final long serialVersionUID = 9001146480969477613L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//super.doGet(request, response);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//使用request对象的getSession()获取session，如果session不存在则创建一个
		HttpSession session = request.getSession();
		//将数据存储到session中
		session.setAttribute("jason", "ShenZiYang");
		//获取session的ID
		String sessionId = session.getId();
		//判断session是不是新创建的
		if(session.isNew()){
			response.getWriter().print("session create successed.Id of Session:"+sessionId);
		}else{
			response.getWriter().println("session existed.Id of session:"+sessionId);
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	
	
	
}
