package com.seomse.commons.communication;
/**
 * <pre>
 *  파 일 명 : HostAddrPort.java
 *  설    명 : HostAddress
 *             Port
 *
 *  작 성 자 : macle
 *  작 성 일 : 2019.10.27
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 by ㈜섬세한사람들. All right reserved.
 */
public class HostAddrPort {
    private String hostAddress;
    private int port;

    /**
     * @return host address
     */
    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    /**
     * @return port number
     */
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
