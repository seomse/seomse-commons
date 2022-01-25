package com.seomse.crawling.core.http;

import java.util.HashMap;
import java.util.Map;

/**
 * 쿠키 관리
 */
public class CookieBox {
	
	private final Map<String , String> cookie = new HashMap<String , String>();
	
	private final String [] domainPatterns = {".com" , ".co.kr" , ".net" , ".kr" , ".me" , ".pe.kr" , ".org", ".xxx", ".bix", ".info", ".name", ".tel", ".co", ".so", ".asia", ".me", ".us"};

	/**
	 * Cookie 데이터 추가
	 * @param url String
	 * @param cookieData String
	 * @return String
	 */
	public String add(String url , String cookieData) {
		if ( cookieData == null || cookieData.length() == 0)
			return url;
		String domain = "";
		String domainKey = "";
		if (cookieData.contains("Domain"))
			domainKey = "Domain";
		else if (cookieData.contains("DOMAIN"))
			domainKey = "DOMAIN";
		else
			domainKey = "domain";
		
		if (!cookieData.contains(domainKey + "=")){
			domain = getDomain(url);
		} else {
			domain = getValue(cookieData , domainKey);
		}
		
		if ( !domain.startsWith(".") )
			domain = "." + domain;
		
		String cookieValue = cookieData.substring(0 ,
				cookieData.contains(";") ?
					cookieData.indexOf(";") :
					cookieData.length())+";";
		
		if ( cookie.containsKey(domain) ) {
			String value = cookie.get(domain);
			
			String cookieValueKey = cookieValue.substring(0 , cookieValue.indexOf("=") );
			
			if ( value.contains(cookieValueKey+"=")){
				value = cookieValue;
			} else {
				value += cookieValue;
			}
			
			cookie.put(domain, value);
		} else {
			cookie.put(domain, cookieValue);
		}
	
		return domain;
	}

	/**
	 * 도메인별 쿠키데이터 조회
	 * @param domain String
	 * @return cookieData String
	 */
	public String get(String domain) {
		if ( domain.startsWith("http") ){
			domain = getDomain(domain);
		}
		
		StringBuilder cookieStr = new StringBuilder();
		
		while(!isLastDomain(domain)){
			if( cookie.get(domain) != null ) {
				cookieStr.append(cookie.get(domain));
			}
			domain = domain.substring( domain.indexOf("." , 1) , domain.length() );
		}
		return cookieStr.toString();
	}

	/**
	 * 마지막 도메인 확인
	 * @param domain String
	 * @return boolean
	 */
	private boolean isLastDomain(String domain) {
		
		int dotLength = domain.length();
		dotLength -= domain.replace(".", "").length();
		
		if ( dotLength < 2)
			return true;
		
		boolean domainFlag = false;
		for(String domainPathern : domainPatterns) {
			if (domainPathern.equals(domain))
				domainFlag = true;
		}
		return domainFlag;
		
	}

	private String getDomain(String url) {
		String domain = url.substring( url.indexOf("://")+3);
		domain = domain.substring(0 , domain.contains("/") ? domain.indexOf("/") : domain.length());
		return "." + domain;
	}

	private String getValue(String target ,String key) {
		if (target.contains(key)){
			String result =  target.substring( target.indexOf(key) + (key.length()+1) , target.length() );
			result = result.substring(0 , result.contains(";") ? result.indexOf(";") : result.length());
			return result;
		} else {
			return "";
		}
	}
}