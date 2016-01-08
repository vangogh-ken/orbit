package org.cc.automate.z.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class TestUnit {
	private final static Logger LOG = LoggerFactory.getLogger(TestUnit.class);
	
	public ApplicationContext applicationContext = null;
	@Test
	public void tt(){
		System.out.println(new SimpleDateFormat("yyMMww").format(Calendar.getInstance(Locale.CHINA).getTime()));
		System.out.println(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
		
		Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		try {
			System.out.println(new SimpleDateFormat("yyMMww").parse("2015-12-28"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void ttest(){
		System.out.println(5/2);
		
		List<Integer> ss = new ArrayList<Integer>();
		ss.add(1);
		ss.add(2);
		ss.add(3);
		ss.add(4);
		ss.add(5);
		ss.add(6);
		ss.add(7);
		ss.add(8);
		ss.add(9);
		ss.add(10);
		ss.add(11);
		ss.add(12);
		ss.add(13);
		ss.add(14);
		ss.add(15);
		
		for(int i : ss.subList(5, 10)){
			System.out.println(i);
		}
		
	}
	
	/*@Test
	public void test(){
		applicationContext = new AnnotationConfigApplicationContext("org.cc");
		System.out.println(applicationContext);
		System.out.println(applicationContext.getBean(JdbcTemplate.class));
	}
	
	@Test
	public void t1(){
		EnvironmentService environmentService = (EnvironmentService)applicationContext.getBean("environmentService");
		environmentService.configV1("d2ca581c-c436-4fd2-bc7f-cf6084d88937");
	}*/
}
