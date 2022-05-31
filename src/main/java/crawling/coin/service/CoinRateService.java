package crawling.coin.service;

import crawling.coin.domain.Coin;
import crawling.coin.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinRateService {

    private final CoinRepository CoinRepository;

    public List<Coin> findIncreaseRate() {
        return CoinRepository.findIncreaseRate();
    }

    public List<Coin> findDecreaseRate() {
        return CoinRepository.findDecreaseRate();
    }

}
