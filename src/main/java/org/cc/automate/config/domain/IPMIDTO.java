package org.cc.automate.config.domain;

public class IPMIDTO {
	private String IPAddress;
	private String SubnetMask;
	private String MACAddress;
	private String DefaultGatewayIP;
	private String  VLANID;
	private String  VLANPriority;
	
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getSubnetMask() {
		return SubnetMask;
	}
	public void setSubnetMask(String subnetMask) {
		SubnetMask = subnetMask;
	}
	public String getMACAddress() {
		return MACAddress;
	}
	public void setMACAddress(String mACAddress) {
		MACAddress = mACAddress;
	}
	public String getDefaultGatewayIP() {
		return DefaultGatewayIP;
	}
	public void setDefaultGatewayIP(String defaultGatewayIP) {
		DefaultGatewayIP = defaultGatewayIP;
	}
	public String getVLANID() {
		return VLANID;
	}
	public void setVLANID(String vLANID) {
		VLANID = vLANID;
	}
	public String getVLANPriority() {
		return VLANPriority;
	}
	public void setVLANPriority(String vLANPriority) {
		VLANPriority = vLANPriority;
	}
	
	
}
