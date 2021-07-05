package chap03;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoService {
	@Autowired
	MemberDao memberDao;
	
	public Member selectOne(String email) {
		Member member = this.memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		return member;
	}
}
