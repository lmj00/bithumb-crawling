package crawling.coin.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class News {

    private String title;
    private String date;
    private String link;

}