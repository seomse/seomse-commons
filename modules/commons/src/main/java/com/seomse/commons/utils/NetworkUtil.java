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

import com.seomse.commons.exception.SocketRuntimeException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.LinkedHashSet;

/**
 * ip, mac 관련 유틸
 * @author macle
 */
public class NetworkUtil {


	/**
	 * hostAddress 정보가 있는 inetAddress 얻기
	 * @param hostAddress String host address
	 * @return InetAddress
	 * @throws SocketException  SocketException
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
	 * @param inetAddress InetAddress
	 * @return String mac address
	 * @throws SocketException SocketException
	 */
	public static String getMacAddress(InetAddress inetAddress) throws SocketException {
		
		
		NetworkInterface network = NetworkInterface.getByInetAddress(inetAddress);
		byte [] macArray  = network.getHardwareAddress();
		if(macArray == null || macArray.length ==0) {
			return "";
		}
		
		return getMacAddress(macArray);
	}
	
	
	/**
	 * 맥주소 얻기
	 * @param macArray byte [] macArray  mac byte array
	 * @return String MacAddress
	 */
	public static String getMacAddress( byte [] macArray ) {
		     
		StringBuilder macAddressBuilder = new StringBuilder();
		for(byte mac : macArray){
			macAddressBuilder.append(String.format("-%02X", mac));
		}
		        
		return macAddressBuilder.toString().substring(1);
	}

	public static String [] getMacAddressArray() {
		LinkedHashSet<String> set = new LinkedHashSet<>();

		try {
			Enumeration<NetworkInterface> networkInterfaceEum = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaceEum.hasMoreElements()){

				NetworkInterface networkInterface = networkInterfaceEum.nextElement();

				byte [] macBytes = networkInterface.getHardwareAddress();

				Enumeration<InetAddress> inetAddressEum = networkInterface.getInetAddresses();

				while(inetAddressEum.hasMoreElements()){
					long t = System.currentTimeMillis();
					InetAddress inetAddress = inetAddressEum.nextElement();

					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
						if(macBytes != null){
							set.add(NetworkUtil.getMacAddress(networkInterface.getHardwareAddress()));
						}
					}
				}
			}
		}catch (SocketException e){
			throw new SocketRuntimeException(e);
		}

		if(set.size() > 0){
			String [] array = set.toArray(new String[0]);
			set.clear();
			return array;

		}

		return new String[0];
	}
	
}
