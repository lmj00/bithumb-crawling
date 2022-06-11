package crawling.coin.repository;

import crawling.coin.domain.Coin;
import crawling.coin.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Coin> {

    List<News> findTopByOrderByDateDesc();

}
