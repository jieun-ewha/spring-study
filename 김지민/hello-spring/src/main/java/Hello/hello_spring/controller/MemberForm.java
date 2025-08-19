package Hello.hello_spring.controller;

import Hello.hello_spring.domain.Member;
import Hello.hello_spring.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;

public class MemberForm {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
