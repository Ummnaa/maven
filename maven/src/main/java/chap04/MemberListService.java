package chap04;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberListService {
	@Autowired
	MemberDao memberDao;
	
	public List<Member> selectList() {
		return memberDao.selectList();
	}
}
