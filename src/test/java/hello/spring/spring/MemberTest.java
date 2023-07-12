package hello.spring.spring;

import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Random;

import static hello.spring.spring.basic.DefaultSetting.initMemberInfo;

@Slf4j
@TestMethodOrder(MethodOrderer.Random.class)
public class MemberTest {

    private final MemberService memberService = new MemberServiceImpl();

    private Member member;

    @BeforeEach
    void before() {
        int randomMember = new Random().nextInt(5);
        member = initMemberInfo.get(randomMember);
    }

    @AfterEach
    void after() {
        memberService.deleteMember(member.getId());
    }

    @RepeatedTest(10)
    void 회원_저장() {
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        log.info("\nmember = {}\nfindMember = {}\n\n", member, findMember);
        Assertions.assertEquals(member, findMember);
    }

}
