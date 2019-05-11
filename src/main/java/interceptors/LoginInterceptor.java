package interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        if (!checkCookie(request.getCookies(), "userid")) {
            request.getRequestDispatcher("/LoginPage").forward(request, response);
            //response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
        return true;
    }

    private boolean checkCookie(Cookie[] cookies, String name) {
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name))
                    return true;
            }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
