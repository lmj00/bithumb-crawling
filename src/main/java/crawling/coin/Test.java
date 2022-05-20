package crawling.coin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {

        Document doc;
        String URL = "https://www.bithumb.com/";
        doc = Jsoup.connect(URL).get();

        List<String> coinList_KRW = new ArrayList<>();
        List<Double> real_KRW = new ArrayList<>();
        List<String> assetRealPrice_KRW = new ArrayList<>();
        List<String> assetRealRate_KRW = new ArrayList<>();
        List<Long> assetRealPrice2_KRW = new ArrayList<>();
        List<String> assetTotalPrice_KRW = new ArrayList<>();

        // small 이름
        Elements smallTxt = doc.select("#sise_list > tbody > tr > td > div > p > a > span");

        int i = 0;

        for (Element ele : smallTxt) {
            i++;

            if (ele.text().substring(ele.text().length() - 3).contains("KRW")) {
                // krw
                String t = ele.text(); // small_txt

                // coin 이름
                coinList_KRW.add(doc.select("#sise_list > tbody > tr:nth-child(" + i + ") > td:nth-child(1) > div > p > a > strong")
                        .text().replace("신규 공시", ""));

                // real_KRW, 가격, 원 제거하고 추가
                real_KRW.add(Double.valueOf(doc.select("#sise_list > tbody > tr:nth-child(" + i + ") > td:nth-child(2) > div")
                        .text().replace(",", "").replace(" 원", "")));

                // 변동 가격
                assetRealPrice_KRW.add(doc.select("#assetRealPrice" + t.substring(0, t.indexOf("/")) + "_KRW")
                        .text().replace("원", "").strip());

                // 변동률, % 제거
                assetRealRate_KRW.add(doc.select("#assetRealRate" + t.substring(0, t.indexOf("/")) + "_KRW")
                        .text().replace("%", "").strip());

                // 거래금액(24H)
                assetRealPrice2_KRW.add(Long.valueOf(doc.select("#sise_list > tbody > tr:nth-child(" + i + ") > td:nth-child(4) > div")
                        .text().replace("≈", "").replace(",", "").replace("원", " ").strip()));

                // 시가 총액
                assetTotalPrice_KRW.add(doc.select("#sise_list > tbody > tr:nth-child(" + i + ") > td:nth-child(5)").text());
            } else {
                // btc
            }
        }


        for (int j = 0; j < coinList_KRW.size(); j++) {
            System.out.println("자산: " + coinList_KRW.get(j) + " 실시간 시세: " + real_KRW.get(j) + " 변동가격: " +
                    assetRealPrice_KRW.get(j) +  " 변동률: " + assetRealRate_KRW.get(j) + " 거래금액(24H): " + assetRealPrice2_KRW.get(j) + " 시가총액: " + assetTotalPrice_KRW.get(j));
        }


        //        Document doc;
//        String URL = "https://upbit.com/exchange?code=CRIX.UPBIT.KRW-BTC";
//        try {
//            doc = Jsoup.connect(URL)
//                    .timeout(100000)
//                    .ignoreHttpErrors(true)
//                    .followRedirects(true)
//                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36")
//                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
//                    .header("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\", \"Google Chrome\";v=\"101\"")
//                    .header("sec-ch-ua-mobile", "?0")
//                    .header("sec-ch-ua-platform", "Windows")
//                    .header("Upgrade-Insecure-Requests", "1")
//                    .method(Connection.Method.GET)
//                    .get();
//            System.out.println(doc);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
