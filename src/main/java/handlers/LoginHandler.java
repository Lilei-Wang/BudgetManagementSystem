package handlers;

import beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IUserService;
import util.CookieUtil;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginHandler {
    @RequestMapping("/LoginPage")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "login.jsp";
    }

    @RequestMapping("/")
    public String indexPage(){
        System.out.println("show index");
        return "index.jsp";
    }

    @Autowired
    private IUserService userService;
    @RequestMapping("/Login")
    public void loginAction(User user,HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(userService.checkUser(user))
        {
            Cookie useridCookie = new Cookie("userid", user.getId().toString());
            useridCookie.setPath("/");
            response.addCookie(useridCookie);
            Cookie usernameCookie = new Cookie("username", user.getName());
            usernameCookie.setPath("/");
            response.addCookie(usernameCookie);
            //response.sendRedirect(request.getContextPath()+"/");
            System.out.println("back to index page");
        }
        else {
            response.sendError(444,"no such user");
        }
    }

    /**
     * 注册用户
     * @param user
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/Register")
    public void registerAction(User user,HttpServletRequest request,HttpServletResponse response) throws IOException {
        if(userService.existUser(user)){
            response.sendError(444,"username already exists.");
            return;
        }
        userService.addUser(user);
        Cookie useridCookie = new Cookie("userid", user.getId().toString());
        useridCookie.setPath("/");
        response.addCookie(useridCookie);
        Cookie usernameCookie = new Cookie("username", user.getName());
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);
        System.out.println("register done");
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/Logout")
    public void logoutAction(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //Cookie cookie=new Cookie("userid",null);
        Cookie cookie= CookieUtil.getCookieByName(request.getCookies(),"userid");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        System.out.println("Userid cookie:"+cookie.getPath());
        response.addCookie(cookie);
        //cookie=new Cookie("username",null);
        cookie=CookieUtil.getCookieByName(request.getCookies(),"username");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        System.out.println("Username cookie:"+cookie.getPath());
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath());
    }
}
