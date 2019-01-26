package service.impl;

import org.springframework.stereotype.Service;
import service.ICrawlerService;

@Service
public class CrawlerService implements ICrawlerService {
    @Override
    public void ForWorkstation() {
        System.out.println("爬取");
    }
}
