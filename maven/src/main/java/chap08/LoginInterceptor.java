package chap08;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
	/*
	 * preHandle: 컨트롤러 실행 전
	 * postHandle: 컨트롤러 실행 후(뷰 리턴 전)
	 * afterCompletion: 뷰 실행 후
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		return session.getAttribute("memberInfo") != null;
	}
}
