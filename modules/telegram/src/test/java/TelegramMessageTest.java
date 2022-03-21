import com.comse.telegram.message.TelegramMessage;
import com.comse.telegram.type.FutureTradeType;

import java.math.BigDecimal;

/**
 * 텔레그램 메세지 샘플 전송
 * @author ccsweets
 */
public class TelegramMessageTest {


    public static void main(String [] args){

//        TelegramMessage.stockMessageToMe("삼성전자",1230300,100,"101-32-13131",823981293,true);
//        TelegramMessage.coinFutureTradeMessageToMe(
//                "BTC", new BigDecimal(30323.13),
//                new BigDecimal(13.1),
//                new BigDecimal(53.1),
//                FutureTradeType.LONG
//        );
        TelegramMessage.coinFutureLiquidationMessageToMe(
                "BTC", new BigDecimal(30323.13), new BigDecimal(3.4),
                new BigDecimal(30123.10),
                new BigDecimal(0),
                FutureTradeType.LONG, "손절"
        );
    }
}
