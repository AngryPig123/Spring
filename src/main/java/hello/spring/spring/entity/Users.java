package hello.spring.spring.entity;

import hello.spring.spring.entity.embaded.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;


@Table
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    public Users(String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name +
                ", grade=" + grade +
                ", createdAt=" + super.createdAt +
                ", updatedAt=" + super.updatedAt +
                '}';
    }

}
