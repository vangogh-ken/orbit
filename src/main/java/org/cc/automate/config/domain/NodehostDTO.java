package org.cc.automate.config.domain;

import java.util.Map;

public class NodehostDTO {
	/**
	 * 主机名
	 */
	private String nodename;
	/**
	 * 部署节点则有改属性且为autodeploy
	 */
	private String role;
	/**
	 * 管理网络信息
	 */
	private Map<String, Object> mgnet;
	/**
	 * IPMI信息
	 */
	private Map<String, Object> ipmi_info;
	/**
	 * 网卡信息
	 */
	private Map<String, Object> ip_interfaces;
	/**
	 * CPU型号
	 */
	private String cpu_model;
	/**
	 * CPU核心数
	 */
	private String num_cpus;
	/**
	 * RAM
	 */
	private String mem_total;	
	/**
	 * 操作系统
	 */
	private String osfinger;
	/**
	 * 系统架构
	 */
	private String osarch;
	/**
	 * 生成厂家
	 */
	private String manufacturer;
	/**
	 * 产品型号
	 */
	private String productname;
	/**
	 * IPMI信息
	
	private IPMIDTO ipmi_info;
	 */
	/**
	 * IPV4信息
	 
	private IPV4DTO ip4_interfaces;
	*/
	/*public IPMIDTO getIpmi_info() {
		return ipmi_info;
	}
	public void setIpmi_info(IPMIDTO ipmi_info) {
		this.ipmi_info = ipmi_info;
	}
	public IPV4DTO getIp4_interfaces() {
		return ip4_interfaces;
	}
	public void setIp4_interfaces(IPV4DTO ip4_interfaces) {
		this.ip4_interfaces = ip4_interfaces;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public String getMem_total() {
		return mem_total;
	}
	public void setMem_total(String mem_total) {
		this.mem_total = mem_total;
	}
	public String getNum_cpus() {
		return num_cpus;
	}
	public void setNum_cpus(String num_cpus) {
		this.num_cpus = num_cpus;
	}
	public String getOsfinger() {
		return osfinger;
	}
	public void setOsfinger(String osfinger) {
		this.osfinger = osfinger;
	}
	public String getCpu_model() {
		return cpu_model;
	}
	public void setCpu_model(String cpu_model) {
		this.cpu_model = cpu_model;
	}
	public String getOsarch() {
		return osarch;
	}
	public void setOsarch(String osarch) {
		this.osarch = osarch;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}*/
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public Map<String, Object> getMgnet() {
		return mgnet;
	}
	public void setMgnet(Map<String, Object> mgnet) {
		this.mgnet = mgnet;
	}
	public Map<String, Object> getIpmi_info() {
		return ipmi_info;
	}
	public void setIpmi_info(Map<String, Object> ipmi_info) {
		this.ipmi_info = ipmi_info;
	}
	public Map<String, Object> getIp_interfaces() {
		return ip_interfaces;
	}
	public void setIp_interfaces(Map<String, Object> ip_interfaces) {
		this.ip_interfaces = ip_interfaces;
	}
	public String getCpu_model() {
		return cpu_model;
	}
	public void setCpu_model(String cpu_model) {
		this.cpu_model = cpu_model;
	}
	public String getNum_cpus() {
		return num_cpus;
	}
	public void setNum_cpus(String num_cpus) {
		this.num_cpus = num_cpus;
	}
	public String getMem_total() {
		return mem_total;
	}
	public void setMem_total(String mem_total) {
		this.mem_total = mem_total;
	}
	public String getOsfinger() {
		return osfinger;
	}
	public void setOsfinger(String osfinger) {
		this.osfinger = osfinger;
	}
	public String getOsarch() {
		return osarch;
	}
	public void setOsarch(String osarch) {
		this.osarch = osarch;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	
	
}
