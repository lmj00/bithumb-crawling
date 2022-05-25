package crawling.coin.controller;

import crawling.coin.domain.Coin;
import crawling.coin.service.CoinDecreaseService;
import crawling.coin.service.CoinIncreaseService;
import crawling.coin.service.CoinParsingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SharpController {

    private final CoinParsingService coinParsingService;
    private final CoinIncreaseService coinIncreaseService;
    private final CoinDecreaseService coinDecreaseService;

    /**
     *
     * 1분마다 동작하는 파싱 엔드포인트
     *
     * @throws IOException
     */

    @Scheduled(cron = "0 */1 * * * *")
    public void coinParsing() throws IOException {
        log.debug("파싱 시작: " + LocalDateTime.now());
        coinParsingService.getCoin();
        log.debug("파싱 끝:" + LocalDateTime.now());
    }


    /**
     * 급상승한 코인 정보를 가져온다.
     *
     * @param model
     * @return
     */
    @RequestMapping("/up")
    public String increaseCoin(Model model) {
        List<Coin> coins = coinIncreaseService.findIncrease();
        List<BigDecimal> increase = coinIncreaseService.rateOfIncrease();
        model.addAttribute("coins", coins);
        model.addAttribute("increase", increase);
        return "sharp/up";
    }


    @RequestMapping("/down")
    public String decreaseCoin(Model model) {
        List<Coin> coins = coinDecreaseService.findDecrease();
        List<BigDecimal> decrease = coinDecreaseService.rateOfDecrease();
        model.addAttribute("coins", coins);
        model.addAttribute("decrease", decrease);
        return "sharp/down";
    }
}