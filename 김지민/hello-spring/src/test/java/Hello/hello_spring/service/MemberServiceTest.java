package Hello.hello_spring.service;
import Hello.hello_spring.repository.MemoryMemberRepository;
import Hello.hello_spring.domain.Member;

import Hello.hello_spring.repository.MemoryMemberRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;

//Assertion 패키지 어려워...
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach//각 테스트 전에 실행
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository(); //같은 리포지토리를 사용할 수 있게 됨!!
        //->dependency injection (DI)
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given(주어진 것)
        Member member = new Member();
        member.setName("hello");

        //when(검증하는 것)
        Long saveId = memberService.join(member);

        //then(나와야 하는 것)
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName()); //업데이트 된듯?

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("abc");

        //when
        Member member2 = new Member();
        member2.setName("abc");

        //then
        memberService.join(member1);
        try{
            memberService.join(member2);
            //fail();<-에러나는데??
        }catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}