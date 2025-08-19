package Hello.hello_spring.service;

import Hello.hello_spring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    /*
    private DataSource dataSource;

    @Autowired //의존성 주입(이 부분 이해 안 됨...javax.sql.DataSource에서 자동으로 의존성을 주입하나?)
    //https://medium.com/%40AlexanderObregon/how-spring-boot-auto-configuration-works-68f631e03948
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    */

    /*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){ //의존성 주입
        this.em = em;
    }

*/
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*
    @Bean //리포지토리 객체를 빈에 등록
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }*/
}
