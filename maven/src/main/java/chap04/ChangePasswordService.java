package chap04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {
	@Autowired
	MemberDao memberDao;
	
	public void changePassword(String email, String oldPassword, String newPassword) {
		Member member = memberDao.selectByEmail(email);
		
		if (member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPassword, newPassword);
		
		memberDao.update(member);
	}
}
