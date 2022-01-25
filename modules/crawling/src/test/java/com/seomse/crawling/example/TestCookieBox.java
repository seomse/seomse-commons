package com.seomse.crawling.example;

import com.seomse.crawling.core.http.CookieBox;

public class TestCookieBox {
	public static void main( String [] args ){
		CookieBox cb = new CookieBox();
//		cb.add("http://nid.naver.com/", "a=1; path='/'; domain=.naver.com;");
		cb.add("http://nid.naver.com/", "");
		cb.add("http://nid.naver.com/", "a=1; path='/';");
		cb.add("http://nid.naver.com/", "a=2; path='/';");
		cb.add("http://nid.naver.com/", "b=1; path='/'; domain=.naver.com");
		cb.add("http://nid.naver.com/", "c=1; path='/'; DOMAIN=nid.naver.com");
		cb.add("http://nid.naver.com/", "d=1; path='/'; Domain=.naver.com");
		cb.add("http://nid.naver.com/", "e=1; path='/'; domain=nid.naver.com");
		cb.add("http://nid.naver.com/", "f=1; path='/'; domain=.naver.com");
		cb.add("http://nid.naver.com/", "g=1; path='/'; domain=nid.naver.com");
		cb.add("http://nid.naver.com/", "x=1; path='/'; domain=cafe.naver.com");

		System.out.println(cb.get("http://cafe.naver.com/"));
		System.out.println();
	}
}
