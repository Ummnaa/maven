package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegsterService() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePasswordService() {
		ChangePasswordService passwordService = new ChangePasswordService();
		passwordService.setMemberDao(memberDao());
		return passwordService;
	}
	
	@Bean
	public MemberListService memberListService() {
		MemberListService memberListService = new MemberListService();
		memberListService.setMemberDao(memberDao());
		return memberListService;
	}
	
	@Bean
	public MemberInfoService memberInfoService() {
		MemberInfoService memberInfoService = new MemberInfoService();
		memberInfoService.setMemberDao(memberDao());
		return memberInfoService;
	}
}
