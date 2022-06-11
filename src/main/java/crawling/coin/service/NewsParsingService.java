package crawling.coin.service;

import crawling.coin.domain.News;
import crawling.coin.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsParsingService {

    private final NewsSaveService newsSaveService;
    private final NewsRepository newsRepository;

    public void getNewsContent() throws IOException {

        Document doc;
        List<String> title = new ArrayList<>();
        List<String> editor = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> link = new ArrayList<>();

        List<News> topByOrderByDateDesc = newsRepository.findTopByOrderByDateDesc();
        int i = 1;


        if (topByOrderByDateDesc.size() > 0) {

        } else {
            while (true) {
                String URL = "https://www.coindeskkorea.com/news/articleList.html?page=" + i;
                doc = Jsoup.connect(URL).get();

                Elements ele = doc.select("div > div.list-titles.table-cell > a > strong");
                Elements ele2 = doc.select("div.list-dated.table-cell");
                Elements ele3 = doc.select(".links");


                if (ele.text().equals("")) {
                    break;
                } else {
                    for (Element element : ele) {
                        title.add(element.text());
                    }

                    for (Element element : ele2) {
                        if (!element.text().equals("")) {
                            editor.add(element.text().split("\\|")[0]);
                            date.add(element.text().split("\\|")[1]);
                        }
                    }

                    for (Element element : ele3) {
                        link.add(element.absUrl("href"));
                    }
                }

                for (int j = 0; j < title.size(); j++) {
                    newsSaveService.save(title.get(j), editor.get(j), date.get(j), link.get(j));
                }

                title.clear();
                editor.clear();
                date.clear();
                link.clear();

                i++;
            }
        }

    }
}