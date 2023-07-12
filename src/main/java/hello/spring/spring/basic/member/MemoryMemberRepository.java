package hello.spring.spring.basic.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long seq = 1L;

    @Override
    public void save(Member member) {
        store.put(seq, member);
        member.setId(seq);
        seq++;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public void deleteMember(Long id) {
        store.remove(id);
    }
}
