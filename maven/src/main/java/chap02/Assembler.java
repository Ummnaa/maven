package chap02;

public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService memberRegisterService;
	private ChangePasswordService changePasswordService;
	private MemberListService selectListService;
	private MemberInfoService memberInfoService;
	public Assembler() {
		memberDao = new MemberDao();
		memberRegisterService = new MemberRegisterService(memberDao);
		changePasswordService = new ChangePasswordService();
		changePasswordService.setMemberDao(memberDao);
		selectListService = new MemberListService();
		selectListService.setMemberDao(memberDao);
		memberInfoService = new MemberInfoService();
		memberInfoService.setMemberDao(memberDao);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getMemberRegisterService() {
		return memberRegisterService;
	}

	public ChangePasswordService getChangePasswordService() {
		return changePasswordService;
	}

	public MemberListService getSelectListService() {
		return selectListService;
	}
	
	public MemberInfoService getMemberInfoService() {
		return memberInfoService;
	}
}
