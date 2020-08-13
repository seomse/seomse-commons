/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seomse.commons.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 예외처리 관련 유틸
 * @author macle
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
