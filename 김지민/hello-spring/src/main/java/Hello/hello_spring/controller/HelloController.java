package Hello.hello_spring.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller //어노테이션
public class HelloController { //요청 받기
    @GetMapping("hello") //정적 페이지
    public String hello(Model model){
        model.addAttribute("data","haha ha");
        return "hello";
    }

    //템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //<-이거 써야됨.
    //api 사용, 문자열 return 시
    public String helloString(@RequestParam("name") String name){
        return "hello"+name; //return 한 것을 그대로 내려줌
    }

    //api 사용, 객체 return 시
    @GetMapping("hello-api")
    @ResponseBody //api 방식
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //json 반환
    }

    public static class Hello{
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
