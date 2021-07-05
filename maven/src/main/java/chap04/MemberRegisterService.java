package chap04;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;
	
	public Long regist(RegisterRequest request) {
		Member member = memberDao.selectByEmail(request.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("이메일 중복:"+request.getEmail());
		}
		Member newMember = new Member(request.getEmail(), request.getPassword(), request.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
