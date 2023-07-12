package hello.spring.spring.basic;

import hello.spring.spring.basic.member.Grade;
import hello.spring.spring.basic.member.Member;

import java.util.List;
import java.util.Map;

public class DefaultSetting {

    public static Map<String, Integer> menuInfoMap = Map.of(
            "짜장면", 7000,
            "짬뽕", 7500,
            "탕수육(소)", 15000,
            "탕수육(중)", 20000,
            "탕수육(대)", 25000
    );

    public static List<String> menuNameList = List.of(
            "짜장면",
            "짬뽕",
            "탕수육(소)",
            "탕수육(중)",
            "탕수육(대)"
    );

    public static List<Member> initMemberInfo = List.of(
            new Member("VIP 테스터1", Grade.VIP),
            new Member("VIP 테스터2", Grade.VIP),
            new Member("BASIC 테스터1", Grade.BASIC),
            new Member("BASIC 테스터2", Grade.BASIC),
            new Member("BASIC 테스터3", Grade.BASIC)
    );

}
