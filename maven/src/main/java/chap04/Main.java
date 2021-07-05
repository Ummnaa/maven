package chap04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	private static AnnotationConfigApplicationContext ctx = null;
	// new hong@gmail.com 홍길동 1234 1234
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.println("명령어를 입력해 주세요.");
			String cmd = reader.readLine();
			
			if (cmd.equals("exit")) {
				System.out.println("종료합니다.");
				break;
			} else if (cmd.startsWith("new")) {
				processNewCommand(cmd.split(" "));
			} else if (cmd.startsWith("change")) {
				processChangeCommand(cmd.split(" "));
			} else if (cmd.equals("list")) {
				processListCommand();
			} else if (cmd.startsWith("info")) {
				processInfoCommand(cmd.split(" "));
			}
		}
	}

	private static void processNewCommand(String[] args) {
		if (args.length != 5) {
			return;
		}
		MemberRegisterService memberRegisterService = (MemberRegisterService)ctx.getBean("memberRegsterService");
		RegisterRequest request = new RegisterRequest();
		
		request.setEmail(args[1]);
		request.setName(args[2]);
		request.setPassword(args[3]);
		request.setConfirmPassword(args[4]);
		
		if (!request.isPasswordEqualToConfirmPassword()) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		try {
			memberRegisterService.regist(request);
			System.out.println("등록완료");
		} catch (DuplicateMemberException e) {
			System.out.println("이메일 중복");
		}
	}
	private static void processChangeCommand(String[] args) {
		if (args.length != 4) {
			return;
		}
		ChangePasswordService changePasswordService = (ChangePasswordService)ctx.getBean("changePasswordService");
		try {
			changePasswordService.changePassword(args[1], args[2], args[3]);
			System.out.println("비밀번호 변경");
		} catch (MemberNotFoundException e) {
			System.out.println("회원이 존재하지 않습니다.");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 비밀번호가 일치하지 않습니다.");
		}
	}
	
	private static void processListCommand() {
		MemberListService selectListService = (MemberListService)ctx.getBean("memberListService");
		List<Member> list = selectListService.selectList();
		if (list.size() == 0) {
			System.out.println("리스트가 없습니다.");
			return;
		}
		for (Member member : list) {
			System.out.printf("%s %s %s\n", member.getEmail(), member.getName(), member.getPassword());
		}
	}
	
	private static void processInfoCommand(String[] args) {
		if (args.length != 2) {
			return;
		}
		 MemberInfoService memberInfoService = (MemberInfoService)ctx.getBean("memberInfoService");
		 try {
			 Member member = memberInfoService.selectOne(args[1]);
			 System.out.printf("email:%s name:%s date:%s\n", 
					 member.getEmail(), 
					 member.getName(), 
					 member.getRegisterDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		 } catch (MemberNotFoundException e) {
			 System.out.println("회원이 존재하지 않습니다.");
		 }
	}
	
}
