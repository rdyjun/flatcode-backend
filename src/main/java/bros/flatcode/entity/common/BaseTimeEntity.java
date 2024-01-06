package bros.flatcode.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = { AuditingEntityListener.class }) // JPA Auditing (자동 값 매핑)
@MappedSuperclass // 부모 클래스는 테이블이랑 매핑하지 않고, 자식클래스에 매핑 정보를 제공할때만 사용
// @MappedSuperclass가 걸려있는 클래스를 상속받는 엔티티들은 해당 클래스(BaseTimeEntity) 안에 있는 멤버변수들 또한 모두 컬럼으로 인식
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
