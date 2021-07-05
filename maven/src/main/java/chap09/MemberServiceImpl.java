package chap09;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;
	
	@Override
	public List<MemberVo> selectList() {
		return memberDao.selectList();
	}
	
	@Override
	public MemberVo login(MemberVo vo) {
		return memberDao.login(vo);
	}
	
	@Override
	public MemberVo mypage(int mno) {
		return memberDao.selectOne(mno);
	}
	
	@Override
	public int update(MemberVo vo) {
		return memberDao.update(vo);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
	public int insert(MemberVo vo, HttpServletRequest request) throws Throwable {
	
		memberDao.insert(vo);
		
		int no = vo.getMno();
		String[] school = request.getParameterValues("school");
		String[] year = request.getParameterValues("year");
		
		// school 데이터를 맵에 담아 입력
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("members_mno", no);
		for (int i = 0; i < school.length; i++) {
			if (!"".equals(school[i]) || !"".equals(year[i])) {
				map.put("school", school[i]);
				map.put("year", year[i]);
				memberDao.insertSchool(map);
			}
		}
		
		return 0;
	}
	
	

}
