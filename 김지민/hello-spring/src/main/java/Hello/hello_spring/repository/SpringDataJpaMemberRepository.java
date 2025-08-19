package Hello.hello_spring.repository;

import Hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//스프링데이터jpa가 구현체를 만들어서 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {
    Optional<Member> findByName(String name);
}