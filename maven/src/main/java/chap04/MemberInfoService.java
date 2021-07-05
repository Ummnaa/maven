package chap04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
