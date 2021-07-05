package chap03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListService {
	@Autowired
	MemberDao memberDao;
	
	public List<Member> selectList() {
		return memberDao.selectList();
	}
}
