package chap08;

import java.util.List;

public interface MemberService {
	List<MemberVo> selectList();
	MemberVo login(MemberVo vo);
	MemberVo mypage(int mno);
	int update(MemberVo vo);
}
