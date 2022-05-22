package crawling.coin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "real_krw")
    private BigDecimal realkrw;
    private Float realPrice;
    private Float realRate;
    private Long tradePrice;
    private Long totalPrice;
    private LocalDateTime regDate;

}
