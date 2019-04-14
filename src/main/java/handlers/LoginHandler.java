package handlers;

import beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginHandler {
    @RequestMapping("/LoginPage")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "login.jsp";
    }

    @Autowired
    private IUserService userService;
    @RequestMapping("/Login")
    public void loginAction(User user,HttpServletRequest request, HttpServletResponse response){
        if(userService.checkUser(user))
        {
            response.addCookie(new Cookie("userid",user.getId().toString()));
            response.addCookie(new Cookie("username",user.getName()));
        }
        else {
            ;
        }
    }
}
