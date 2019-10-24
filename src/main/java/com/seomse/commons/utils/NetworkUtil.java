
package com.seomse.commons.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * <pre>
 *  파 일 명 : NetworkUtil.java
 *  설    명 : 네티워크 유틸성 클래스
 *
 *  작 성 자 : macle
 *  작 성 일 : 2018.05
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2018 by ㈜섬세한사람들. All right reserved.
 */
public class NetworkUtil {


	/**
	 * hostAddress 정보가 있는 inetAddress 얻기
	 * @param hostAddress hostAddress
	 * @return inetAddress
	 */
	public static InetAddress getInetAddress(String hostAddress ) throws SocketException{


		for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
			NetworkInterface intf = en.nextElement();

			for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();){
				InetAddress inetAddress = enumIpAddr.nextElement();


				if(inetAddress.getHostAddress().equals(hostAddress)){
					return inetAddress;
				}
			}
		}



		return null;
	}

	/**
	 * 맥주소 얻기
	 * @param inetAddress inetAddress
	 * @return MacAddress
	 */
	public static  String getMacAddress(InetAddress inetAddress) throws SocketException {
		
		
		NetworkInterface network = NetworkInterface.getByInetAddress(inetAddress);
		byte [] macArray  = network.getHardwareAddress();
		if(macArray == null || macArray.length ==0) {
			return "";
		}
		
		return getMacAddress(macArray);
	}
	
	
	/**
	 * 맥주소 얻기
	 * @param macArray mac byte array
	 * @return MacAddress
	 */
	public static String getMacAddress( byte [] macArray ) {
		     
		StringBuilder macAddressBuilder = new StringBuilder();
		for(byte mac : macArray){
			macAddressBuilder.append(String.format("-%02X", mac));
		}
		        
		return macAddressBuilder.toString().substring(1);
	}
	
	
	
}
