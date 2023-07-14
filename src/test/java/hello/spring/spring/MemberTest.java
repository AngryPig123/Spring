package hello.spring.spring;

import hello.spring.spring.basic.AppConfig;
import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

import static hello.spring.spring.basic.CommonUtil.getFormat;
import static hello.spring.spring.basic.DefaultSetting.initMemberInfo;
import static hello.spring.spring.basic.TextColor.BLUE;

@Slf4j
@TestMethodOrder(MethodOrderer.Random.class)
public class MemberTest {

    private ApplicationContext applicationContext;


    private MemberService memberService;

    private Member member;

    @BeforeEach
    void before() {

        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);

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
        log.info(getFormat(BLUE, "\nmember = {}\nfindMember = {}\n\n"), member, findMember);
        Assertions.assertEquals(member, findMember);
    }

}
