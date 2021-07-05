package chap04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"chap04"})
public class AppCtx {
	@Bean
	public MemberRegisterService memberRegsterService() {
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePasswordService() {
		return new ChangePasswordService();
	}
	
	@Bean
	public MemberListService memberListService() {
		return new MemberListService();
	}
	
	@Bean
	public MemberInfoService memberInfoService() {
		return new MemberInfoService();
	}
}
