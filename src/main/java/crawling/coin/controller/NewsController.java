package crawling.coin.controller;

import crawling.coin.domain.News;
import crawling.coin.service.NewsParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsParsingService newsParsingService;

    @RequestMapping("/news")
    public String news(Model model) throws IOException {

        List<News> newsContent = newsParsingService.getNewsContent();
        model.addAttribute(newsContent);

        return "etc/news";
    }

}
