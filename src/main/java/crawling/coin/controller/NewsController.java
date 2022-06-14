package crawling.coin.controller;

import crawling.coin.domain.News;
import crawling.coin.service.news.NewsGetService;
import crawling.coin.service.news.NewsParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsParsingService newsParsingService;
    private final NewsGetService newsGetService;

    @RequestMapping("/news")
    public String news(Model model, Pageable pageable) throws IOException {
        newsParsingService.getNewsContent();

        Page<News> boardList = newsGetService.getBoardList(pageable);
        model.addAttribute("boardList", boardList);

        return "etc/news";
    }


}