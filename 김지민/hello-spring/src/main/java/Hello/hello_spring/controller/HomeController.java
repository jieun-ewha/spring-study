package Hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//자동 import 어케 함??
@Controller
public class HomeController {
    @GetMapping("/")//localhost:8080으로 들어왔을 때
    public String home(){
        return "home";//템플릿에 home.html이 있으면 그것을 찾기
    }
}
