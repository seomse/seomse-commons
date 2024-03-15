package com.seomse.commons.http;
/**

 * @author macle
 */
public class HttpUtils {
    public static String removeHtmlHelp(String script){

        String str = script;

        for(;;){
            int index = str.indexOf("<!--");
            if(index == -1){
                break;
            }

            int end = str.indexOf("-->", index + 4);
            if(end == -1){
                break;
            }
            str = str.substring(0, index) +
                    str.substring(end + 3);

        }

        return str;

    }

    public static String removeParam(String path, String param){
        int index = path.indexOf("?" + param +"=");
        if(index >= 0){
            int endIndex = path.indexOf('&', index);
            if(endIndex > index){
                path = path.substring(0, index +1) + path.substring(endIndex+1);
            }
        }

        index = path.indexOf("&" + param +"=");
        if(index >= 0){
            int endIndex = path.indexOf('&', index+("&" + param +"=").length());
            if(endIndex == -1){
                path = path.substring(0,index);
            }else{


                path = path.substring(0,index) + path.substring(endIndex);
            }

        }
        return path;
    }


    public static String removeNullParam(String path){
        String paramsValue = path.substring(path.indexOf("?") + 1);
        String [] params = paramsValue.split("&");
        for (int i = 0; i <params.length ; i++) {
            if("".equals(params[i])){
                continue;
            }
            int index = params[i].indexOf('=');
            if(index < 0){
                continue;
            }

            params[i] = params[i].substring(0,index);

            String paramValue = getPramValue(path, params[i]);
            if(paramValue == null || "".equals(paramValue.trim())){
                path = removeParam(path, params[i]);
            }
        }

        return path;
    }

    public static String getPramValue(String path, String param){

        String searchText = "?" + param +"=";
        int index = path.indexOf(searchText);
        if(index >= 0){
            int endIndex = path.indexOf('&', index+ searchText.length());

            if(endIndex < 0){
                return path.substring(index + searchText.length() );
            } else if(endIndex > index){
                return path.substring(index + searchText.length() , endIndex);
            }
        }
        searchText = "&" + param +"=";
        index = path.indexOf(searchText);


        if(index >= 0){
            int endIndex = path.indexOf('&', index + searchText.length());

            if(endIndex < 0){
                return path.substring(index + searchText.length() );
            } else if(endIndex > index){
                return path.substring(index + searchText.length() , endIndex);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "https://fines.fss.or.kr/minwon/am/AmShareUpdate.jsp?sRecp_no=&inqui_man_rgno1=&inqui_man_rgno2=&order=&cncl_div=&day_div=Q&data_share_div=&recp_no=2023a0283&fin_inst_cd=0012452&req_no=2&deal_dept_cd=217021000&sub_menu_title=민원자료공유";

        System.out.println(getPramValue(url, "req_no"));
        System.out.println(removeNullParam(url));

    }

}
