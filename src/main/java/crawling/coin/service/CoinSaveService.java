package crawling.coin.service;

import crawling.coin.domain.Coin;
import crawling.coin.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinSaveService {

    private final CoinRepository coinRepository;

    public void save(
            List<String> coinSmall_KRW,
            List<String> coinList_KRW,
            List<BigDecimal> real_KRW,
            List<Float> assetRealPrice_KRW,
            List<Float> assetRealRate_KRW,
            List<Long> tradePrice_KRW,
            List<Long> assetTotalPrice_KRW
    ) {

        for (int j = 0; j < coinList_KRW.size(); j++) {
            Coin coin = Coin.builder()
                    .smallName(coinSmall_KRW.get(j))
                    .name(coinList_KRW.get(j))
                    .realkrw(real_KRW.get(j))
                    .realPrice(assetRealPrice_KRW.get(j))
                    .realRate(assetRealRate_KRW.get(j))
                    .tradePrice(tradePrice_KRW.get(j))
                    .totalPrice(assetTotalPrice_KRW.get(j))
                    .regDate(LocalDateTime.now())
                    .build();
            coinRepository.save(coin);
        }
    }
}
