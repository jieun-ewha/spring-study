package Hello.hello_spring.service;

import Hello.hello_spring.repository.MemberRepository;
import Hello.hello_spring.repository.MemoryMemberRepository;
import Hello.hello_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

//optinal 클래스는 모든 타입의 객체를 담을 수 있는 래퍼 클래스이다.


public class MemberService {
    //클래스에 final을 붙이면 다른 클래스가 상속할 수 없는 클래스가 된다.
    private final MemberRepository memberRepository;

    //외부에서 repository를 넣어 주도록 변경.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //회원가입. 이름이 같은 멤버는 회원가입 불가
    public Long join(Member member){
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    //전체 회원 조회.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        //Optinal<Member> result = memberRepository.findById(member.getId());

        //result.ifPresent(m->{ //isPresent() 메소드는 Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
        //throw new IllegalAccessException("이미 존재하는 회원입니다");
        //});

        //isPresent() 메소드는 Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
