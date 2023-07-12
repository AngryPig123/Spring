package hello.spring.spring.basic.member;


import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {

    private Long id;
    private String name;
    private Grade grade;

    public Member(String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(getName(), member.getName()) && getGrade() == member.getGrade();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getGrade());
    }

}
