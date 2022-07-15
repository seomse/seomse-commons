package com.seomse.crawling.proxy.api;

import com.google.gson.Gson;
import com.seomse.api.ApiMessage;
import com.seomse.api.Messages;
import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.crawling.core.http.HttpUrl;
import org.json.JSONObject;
/**
 * HttpMessage proxy node api
 * @author macle
 */
public class HttpMessage  extends ApiMessage {

    @Override
    public void receive(String message) {
        try {
            JSONObject messageObj = new JSONObject(message);
            JSONObject optionData = null;
            if(!messageObj.isNull("option_data")) {
                Object obj = messageObj.get("option_data");
                if(obj.getClass() == String.class){
                    optionData = new JSONObject((String) obj);
                }else{
                    optionData = (JSONObject)obj;
                }
            }
            Gson gson = new Gson();
            sendMessage(Messages.SUCCESS+ gson.toJson(HttpUrl.getMessage(messageObj.getString("url"), optionData)));
        }catch(Exception e) {
            sendMessage(Messages.FAIL + ExceptionUtil.getStackTrace(e));
        }
    }

}
