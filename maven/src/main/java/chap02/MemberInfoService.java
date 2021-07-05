package chap02;

public class MemberInfoService {
	MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Member selectOne(String email) {
		Member member = this.memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		return member;
	}
}
