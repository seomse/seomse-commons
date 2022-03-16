package com.comse.telegram.message;

import com.comse.telegram.config.YmlConfig;
import com.comse.telegram.type.FutureTradeType;
import com.seomse.commons.utils.ExceptionUtil;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 정규화된 텔레그램 메세지 전송
 * @author ccsweets
 */
public class TelegramMessage {

    private static final Logger logger = getLogger(TelegramMessage.class);

    private static String token = "";
    private static String chatId = "";
    static {
        token = YmlConfig.getYmlMap("telegram").get("token").toString();
        chatId = YmlConfig.getYmlMap("telegram").get("chat_id").toString();

    }

    /**
     * Telegram message to me
     * @param text text
     * @return
     */
    public static String toMe(String text){
        return toMe(token , chatId , text);
    }

    /**
     * Telegram message to me
     * @param token token
     * @param chatId chatId
     * @param text text
     * @return
     */
    public static String toMe(String token , String chatId , String text){

        BufferedReader in = null;
        StringBuilder response = new StringBuilder();
        try {
            String utfText = URLEncoder.encode(text,"UTF-8");
            URL obj = new URL("https://api.telegram.org/bot" + token + "/sendmessage?chat_id=" + chatId + "&parse_mode=markdown&text=" + utfText); // 호출할 url

            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String line;


            while((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }

        } catch(Exception e) {
            logger.error(ExceptionUtil.getStackTrace(e));
        } finally {
            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
        }
        return response.toString();
    }

    /**
     * 주식 메세지를 자기 텔레그램 API 로 보낸다.
     * @param itemName 종목명
     * @param price 가격
     * @param volume 수량
     * @param depositNum     계좌번호
     * @param depositVolume 계좌잔고
     * @param isSell 판매여부
     * @return
     */
    public static String stockMessageToMe(String itemName , int price , int volume , String depositNum , int depositVolume , boolean isSell){
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.KOREA);
        String priceStr = nf.format(price);
        String depositVolumeStr = nf.format(depositVolume);
        return toMe(
                String.format("\\[%s]\n*%s*   __%s__원   %s__%s__주\n계좌잔액(%s) : \\[__%s__]원", isSell?"매도":"매수", itemName,priceStr,  isSell?"-":"+" , volume+"",depositNum,depositVolumeStr)
        );
    }

    /**
     * 코인 선물 거래 메세지를 자기 텔레그램 API 로 보낸다.
     * @param symbol 심볼
     * @param price 기준 가격
     * @param tradePrice 행사 가격
     * @param depositVolume 계좌 잔고
     * @param futureTradeType 선물 거래 유형
     * @return
     */
    public static String coinFutureTradeMessageToMe(String symbol , BigDecimal price ,
                                                    BigDecimal tradePrice , BigDecimal depositVolume , FutureTradeType futureTradeType){
        String priceStr = price.setScale(2, RoundingMode.DOWN).toString();
        String volumeStr = tradePrice.setScale(2, RoundingMode.DOWN).toString();
        String depositVolumeStr = depositVolume.setScale(2, RoundingMode.DOWN).toString();
        return toMe(
                String.format("\\[*%s*]" +
                                "\n%s" +
                                "\n기준 : %s" +
                                "\n금액 : %s" +
                                "\n잔고 : %s",
                        futureTradeType.name(),
                        symbol,
                        priceStr,
                        volumeStr,
                        depositVolumeStr
                )
        );
    }

    /**
     * 코인 청산 메세지를 자기 텔레그램 API 로 보낸다.
     * @param symbol 심볼
     * @param price 기준 가격
     * @param tradePrice 행사 가격
     * @param depositVolume 계좌 잔고
     * @param futureTradeType 선물 거래 유형
     * @return
     */
    public static String coinFutureLiquidationMessageToMe(String symbol , BigDecimal price , double profit,
                                                    BigDecimal tradePrice , BigDecimal depositVolume , FutureTradeType futureTradeType){
        String priceStr = price.setScale(2, RoundingMode.DOWN).toString();
        String volumeStr = tradePrice.setScale(2, RoundingMode.DOWN).toString();
        String depositVolumeStr = depositVolume.setScale(2, RoundingMode.DOWN).toString();
        String profitStr = String.format("%s%.2f",( profit > 0.0 ? "+" : "" ), profit);
        return toMe(
                String.format("\\[*%s* 청산 => %s]" +
                                "\n%s" +
                                "\n기준 : %s" +
                                "\n금액 : %s" +
                                "\n잔고 : %s",
                        futureTradeType.name(),
                        profitStr,
                        symbol,
                        priceStr,
                        volumeStr,
                        depositVolumeStr
                )
        );
    }
}
