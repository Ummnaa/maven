package chap06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	
	@RequestMapping("/test.do")
	public void test(HttpServletResponse respones) throws IOException {
		respones.setContentType("text/html;charset=utf-8");
		PrintWriter out = respones.getWriter();
//		out.print("test");
		out.print("<script>alert('정상적으로 저장되었습니다.');</script>");
	}

	@RequestMapping("/")
	public String index() {
		return "redirect:index.do";
	}
	
	@RequestMapping("/form.do")
	public String form() {
		return "form";
	}
	
	@RequestMapping("/send.do")
	public String send(Model model, HttpServletRequest request, 
			@RequestParam(value = "name", required = false) String name2, 
			@RequestParam(value = "email", required = false) String email2,
			@RequestParam(value = "no", required = false, defaultValue = "0") int no,
			MemberVo vo) {
		// 1. HttpServletRequest
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		// 2. @RequestParam
		// 	  @RequestParam("파라미터명") : 매개변수에 지정
		System.out.println(vo.getHobby().length);
		for (int v : vo.getHobby()) {
			System.out.println(v);
		}
		
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("name2", name2);
		model.addAttribute("email2", email2);
		model.addAttribute("no", no);
		// 3. 커멘드객체
		model.addAttribute("vo", vo);
		
		if (email == null || "".equals(email)) {
			return "form";
		}
		return "send";
	}
	
	@RequestMapping("main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "홍길동");
		mav.setViewName("main");
		return mav;
	}
}
