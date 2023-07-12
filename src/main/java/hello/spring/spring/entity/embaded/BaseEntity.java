package hello.spring.spring.entity.embaded;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  //  Entity를 DB에 적용하기 이전, 이후에 커스텀 콜백을 요청할 수 있는 어노테이션
public abstract class BaseEntity {

    @CreatedDate    //  데이터 생성 날짜 자동 저장 어노테이션
    @Column(name = "create_at", updatable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate   //  데이터 수정 날짜 자동 저장 어노테이션
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

}
