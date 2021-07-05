package chap04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	private static long nextId = 0;
	private Map<String, Member> map = new HashMap<String, Member>();
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	public void delete(String email) {
		map.remove(email);
	}
	
	public List<Member> selectList() {
		List<Member> list = new ArrayList<Member>();
		for (String key : map.keySet()) {
			list.add(map.get(key));
		}
		return list;
	}
}
