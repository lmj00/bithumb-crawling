package crawling.coin.service.news;

import crawling.coin.domain.News;
import crawling.coin.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NewsSaveService {
    private final NewsRepository newsRepository;

    public void save(
            String title,
            String editor,
            String date,
            String link
    ) {
            News news = News.builder()
                    .title(title)
                    .editor(editor)
                    .date(date)
                    .link(link)
                    .build();

            newsRepository.save(news);
    }
}

