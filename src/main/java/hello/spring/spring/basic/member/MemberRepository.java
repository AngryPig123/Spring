package hello.spring.spring.basic.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long id);

    void deleteMember(Long id);

}
