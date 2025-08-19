package Hello.hello_spring.controller;
import Hello.hello_spring.domain.Member;
import Hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import java.util.*;

//컴포넌트와 관련된 어노테이션이 있으면 스프링이 컨테이너에 등록 -> 오토와이어드가 연결
@Controller
public class MemberController {
    private final MemberService memberService;

    //생성자 주입
    @Autowired //멤버 서비스를 넣을 때, 빈에 등록한 멤버 서비스를 넣어줌
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")//value를 안 붙이면 안 되나?
    public String createForm() {
        return "members/createMemberForm";//경로를 정확히!!(경로를 정확히 안 해서 오류났음...)(처음에 템플릿 폴더에 바로 크리에이트 어쩌고 html 파일 만듦...)
    }

    @PostMapping(value = "members/new")//이 경로로 접근했을 때~~ + post로 넘어왔을 때
    public String create(MemberForm form){//Memberform 객체에 스프링이 post로 받은 값을 넘겨줌+create 함수 실행
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }//메모리에 데이터를 저장->자바 껐다키면 회원 데이터 사라짐.
}
