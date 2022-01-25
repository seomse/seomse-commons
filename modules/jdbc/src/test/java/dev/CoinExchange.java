package dev;

import com.seomse.jdbc.annotation.Column;
import com.seomse.jdbc.annotation.DateTime;
import com.seomse.jdbc.annotation.PrimaryKey;
import com.seomse.jdbc.annotation.Table;
import com.seomse.jdbc.objects.JdbcObjects;

import java.util.List;

@Table(name="coinmarketcap_coin")
public class CoinExchange {

    @PrimaryKey(seq = 1)
    @Column(name = "id")
    long id;

    @Column(name = "slug")
    String slug;

    @Column(name = "market_cap_rank")
    Integer marketCapRank;


    @Column(name = "exchanges")
    String exchanges;

    @DateTime
    @Column(name = "exchange_updated_at")
    Long exchangeUpdatedAt;

    public static void main(String[] args) {
        List<CoinExchange> coinExchangeList = JdbcObjects.getObjList(CoinExchange.class);
    }
}
