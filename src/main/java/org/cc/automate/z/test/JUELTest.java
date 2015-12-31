package org.cc.automate.z.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cc.automate.core.el.JuelFactory;

public class JUELTest {
    public static void main(String[] args) {
    	Map<String, Object> variables = new HashMap<String, Object>();
    	
    	Map<String, Object> item = new HashMap<String, Object>();
    	item.put("K1", "cc1vvvvvvv");
    	item.put("K2", "172.16.70.42");
    	variables.put("zz", item);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> node1 = new HashMap<String, Object>();
    	node1.put("ZJM", "nc5.chinacloud.com");
    	node1.put("CCWK", "eth2");
    	node1.put("CCWLIP", "192.168.100.195");
    	node1.put("CCWLYM", "255.225.225.0");
    	node1.put("GLWLIP", "172.16.70.12");
    	Map<String, Object> node2 = new HashMap<String, Object>();
    	node2.put("ZJM", "nc6.chinacloud.com");
    	node2.put("CCWK", "eth2");
    	node2.put("CCWLIP", "192.168.100.196");
    	node2.put("CCWLYM", "255.225.225.0");
    	node2.put("GLWLIP", "172.16.70.13");
    	list.add(node1);
    	list.add(node2);
    	
    	variables.put("nodehosts", list);
    	
    	JuelFactory.juelFactory().getValue("C:\\T\\config_template_v1.sls", "C:\\T\\ddd.sls", variables);
    }
}