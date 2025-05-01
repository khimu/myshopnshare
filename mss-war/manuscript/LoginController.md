# LoginController

```java

package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Counter;
import com.myshopnshare.core.service.CounterService;
import com.myshopnshare.core.service.PersonService;

@Controller
public class LoginController { //implements Controller {
	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private CounterService counterService;
	@Autowired
	private PersonService personService;

	@RequestMapping("/login.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		InetAddress thisIp = InetAddress.getLocalHost();
		
		Counter counter = counterService.findGeneralCounter();

		if (counter == null) {
			counter = new Counter();
			IpAddress ad = new IpAddress();
			ad.setAddress(thisIp.getHostAddress());
			ad.setName(thisIp.getHostName());
			ad.setCounter(counter);
			counter.getHosts().add(ad);
			counter.increment();
			counterService.saveOrUpdate(counter);
		}

		IpAddress ad = counter.findIpAddress(thisIp.getHostAddress());
		if(ad == null){
			ad = new IpAddress();
			ad.setAddress(thisIp.getHostAddress());
			ad.setName(thisIp.getHostName());
			ad.setCounter(counter);
			counter.getHosts().add(ad);		
			counter.increment();
			counterService.saveOrUpdate(counter);			
		}else{
			Date today = new Date();
			int day = today.getDay();
			int lastLoggedInDay = ad.getLastLoggedIn().getDay();
			if(lastLoggedInDay < day){
				counter.increment();
				counterService.saveOrUpdate(counter);
			}
		}
		*/


		Counter counter = counterService.findGeneralCounter();

		counter.increment();
		counterService.saveOrUpdate(counter);
		request.setAttribute("counter", counter);		
		request.setAttribute("merchants", personService.findMerchants());
		return new ModelAndView("login/login");
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
}```
