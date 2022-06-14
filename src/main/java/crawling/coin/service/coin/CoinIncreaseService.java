package crawling.coin.service.coin;

import crawling.coin.domain.Coin;
import crawling.coin.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CoinIncreaseService {

    private final CoinRepository coinRepository;

    List<BigDecimal> increase = new ArrayList<>();


    /**
     * 1분 기준으로 1% 이상 상승한 코인들을 가져옴
     * @return 
     */
    public List<Coin> findIncrease() {
        List<Coin> fiveCoins = coinRepository.fiveMinuteCoin();
        List<Coin> recentCoins = coinRepository.findRecentCoin();
        increase.clear();

        List<Coin> rankingCoin = new ArrayList<>();
        for (Coin five : fiveCoins) {
            for (Coin recent : recentCoins) {
                if (five.getName().equals(recent.getName())) {
                    if (five.getRealkrw().add(five.getRealkrw().multiply(BigDecimal.valueOf(0.01))).compareTo(recent.getRealkrw()) < 0) {
                        rankingCoin.add(recent);
                        increase.add(
                                (recent.getRealkrw().subtract(five.getRealkrw())
                                        .divide(five.getRealkrw(), 4, RoundingMode.HALF_UP)).
                                        multiply(BigDecimal.valueOf(100))
                        );
                        break;
                    }
                }
            }
        }

        return rankingCoin;
    }

    public List<BigDecimal> rateOfIncrease() {
        return increase;
    }
}
