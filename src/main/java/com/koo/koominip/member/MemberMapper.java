package com.koo.koominip.member;

import java.util.List;

public interface MemberMapper {
	
	public abstract List<Member> checkId(Member m);
	public abstract int join(Member m);
	public abstract int updateInfo(Member m);
	public abstract int withdraw(Member m);
}
