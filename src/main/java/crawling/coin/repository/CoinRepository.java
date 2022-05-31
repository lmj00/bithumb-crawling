package crawling.coin.repository;

import crawling.coin.domain.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {


    /**
     * 1분전 코인 데이터를 가져온다.
     *
     */

    @Query(value = "select * from coin where reg_date >= now() - interval 2 minute", nativeQuery = true)
    List<Coin> fiveMinuteCoin();

    @Query(value = "SELECT * from coin order by reg_date desc limit 200", nativeQuery = true)
    List<Coin> findRecentCoin();

    // 상승 변동률
    @Query(value = "select * from coin where reg_date >= now() - interval 1 minute order by real_rate desc limit 5", nativeQuery = true)
    List<Coin> findIncreaseRate();

    // 하락 변동률
    @Query(value = "select * from coin where reg_Date >= now() - interval 1 minute order by real_rate limit 5", nativeQuery = true)
    List<Coin> findDecreaseRate();
}