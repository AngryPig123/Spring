package hello.spring.spring.basic.singleton5;

import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.member.MemberServiceImpl;
import hello.spring.spring.basic.member.MemoryMemberRepository;

public class MemberServiceToSingleton {

    private MemberServiceToSingleton() {
    }

    private static class SingletonHelper {
        private static final MemberService UNIQUE_MEMBER_SINGLE_TON_SERVICE = new MemberServiceImpl(
                new MemoryMemberRepository()
        );
    }

    public static MemberService getInstance() {
        return SingletonHelper.UNIQUE_MEMBER_SINGLE_TON_SERVICE;
    }

}
