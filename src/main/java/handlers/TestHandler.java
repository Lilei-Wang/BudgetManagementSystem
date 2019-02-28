package handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestHandler {
    @RequestMapping("/Test")
    public String testHandler(){
        return "/test.jsp";
    }
}
