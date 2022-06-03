package crawling.coin.service;

import crawling.coin.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CoinParsingService {

    private final CoinSaveService coinSaveService;

    public void getCoin() throws IOException {
        Document doc;
        String URL = "https://www.bithumb.com/";
        doc = Jsoup.connect(URL).get();

        List<String> coinSmall_KRW = new ArrayList<>();
        List<String> coinList_KRW = new ArrayList<>();
        List<BigDecimal> real_KRW = new ArrayList<>();
        List<Float> assetRealPrice_KRW = new ArrayList<>();
        List<Float> assetRealRate_KRW = new ArrayList<>();
        List<Long> tradePrice_KRW = new ArrayList<>();
        List<Long> assetTotalPrice_KRW = new ArrayList<>();


        // small 이름
        Elements smallTxt = doc.select("#sise_list > tbody > tr > td > div > p > a > span");

        int i = 0;


        for (Element ele : smallTxt) {
            i++;

            if (ele.text().substring(ele.text().length() - 3).contains("KRW")) {

                // small
                coinSmall_KRW.add(ele.text().split("/")[0]);

                // krw
                String t = ele.text(); // small_txt

                // coin 이름
                coinList_KRW.add(doc.select("#sise_list > tbody > tr:nth-child(" + i + ") > td:nth-child(1) > div > p > a > strong")
                        .text().replace("신규 공시", ""));

                // real_KRW
                BigDecimal big = new BigDecimal(doc.select("#sise_list > tbody > tr:nth-child(" + i + ") > td:nth-child(2) > div")
                        .text().replace(",", "").replace(" 원", ""));
//                System.out.println(big);
                real_KRW.add(big);


                // 변동 가격
                assetRealPrice_KRW.add(Float.valueOf(doc.select("#assetRealPrice" + t.substring(0, t.indexOf("/")) + "_KRW")
                        .text().replace("원", "").replace(",", "").strip()));

                // 변동률
                assetRealRate_KRW.add(Float.valueOf(doc.select("#assetRealRate" + t.substring(0, t.indexOf("/")) + "_KRW")
                        .text().replace("%", "").strip()));

                // 거래금액(24H)
                tradePrice_KRW.add(Long.valueOf(doc.select("#sise_list > tbody > tr:nth-child(" + i + ") > td:nth-child(4) > div")
                        .text().replace("≈", "").replace(",", "").replace("원", " ").strip()));

                // 시가 총액
                assetTotalPrice_KRW.add(Long.parseLong(doc.select("#sise_list > tbody > tr:nth-child(" + i + ") > td:nth-child(5)")
                        .text().replace("조", "").replace("억", "").replace("-", "0").replace(" ", "")) * 100_000_000);

            }
        }

        coinSaveService.save(coinSmall_KRW, coinList_KRW, real_KRW, assetRealPrice_KRW, assetRealRate_KRW, tradePrice_KRW, assetTotalPrice_KRW);
    }
}
