package crawling.coin.controller;

import crawling.coin.domain.Coin;
import crawling.coin.service.CoinRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RateController {

    private final CoinRateService coinRateService;

    @RequestMapping("/top-rate")
    public String rate(Model model) {
        List<Coin> increaseRates = coinRateService.findIncreaseRate();
        List<Coin> decreaseRates = coinRateService.findDecreaseRate();

        model.addAttribute("increaseRates", increaseRates);
        model.addAttribute("decreaseRates", decreaseRates);

        return "etc/top_rate";
    }

}
