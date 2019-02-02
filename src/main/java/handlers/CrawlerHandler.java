package handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ICrawlerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Crawler")
public class CrawlerHandler {

    @Autowired
    private ICrawlerService crawlerService;

    @RequestMapping("/Workstation")
    public void ForWorkstation(HttpServletRequest request, HttpServletResponse response)
    {
        crawlerService.ForWorkstation();
        System.out.println("111");
    }
}
