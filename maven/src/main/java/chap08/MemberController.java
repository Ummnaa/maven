package chap08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member/index.do")
	public String index(Model model) {
		model.addAttribute("list", memberService.selectList());
		return "member/index";
	}
	
	@RequestMapping("/member/form.do")
	public String form(MemberVo vo, @CookieValue(value = "cookieEmail", required = false) Cookie cookie) {
		if (cookie != null) {
			vo.setEmail(cookie.getValue());
			vo.setCheckEmail("check");
		}
		return "/member/form";
	}
	
	@RequestMapping("/member/login.do")
	public String login(MemberVo vo, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberVo m = memberService.login(vo);
		if (m != null) {
			// 세션에 저장
			session.setAttribute("memberInfo", m);
			// 쿠키에 저장
			Cookie cookie = new Cookie("cookieEmail", vo.getEmail());
			cookie.setPath("/");
			if ("check".equals(vo.getCheckEmail())) {
				cookie.setMaxAge(60*60*24*365);
			} else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			
			return "redirect:/member/index.do";
		} else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter(); 
			out.print("<script>");
			out.print("alert('이메일과 비밀번호가 올바르지 않습니다.');");
			out.print("location.href='form.do';");
			out.print("</script>");
			return null;
		}
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session, HttpServletResponse response, Model model) throws IOException {
		session.invalidate();
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "form.do");
		
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter(); 
//		out.print("<script>");
//		out.print("alert('로그아웃 되었습니다.');");
//		out.print("location.href='index.do';");
//		out.print("</script>");
		return "/include/alert";
	}
	
	@RequestMapping("/member/mypage.do")
	public String mypage(Model model, HttpSession session) {
		MemberVo vo = (MemberVo)session.getAttribute("memberInfo");
		MemberVo m = memberService.mypage(vo.getMno());
		model.addAttribute("vo", m);
		return "/member/mypage";
	}
	
	@RequestMapping("/member/update.do")
	public String update(Model model, HttpSession session, MemberVo vo) {
		memberService.update(vo);
		
		session.setAttribute("memberInfo", memberService.mypage(vo.getMno()));
		
		model.addAttribute("msg", "수정 되었습니다.");
		model.addAttribute("url", "index.do");
		
		return "/include/alert";
	}
}
