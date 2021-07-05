package chap02;

import java.util.List;

public class MemberListService {
	MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public List<Member> selectList() {
		return memberDao.selectList();
	}
}
