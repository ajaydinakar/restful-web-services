package com.ajayjava.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource messageSource;
	@GetMapping(path="/helloworld")
	public String hellowWorld1()
	{
		
		return "Hellow World";
	}
	@GetMapping(path="/helloBean")
	public HelloWorldBean hellowWorld()
	{
		
		return new HelloWorldBean("Hellow World");
	}
	@GetMapping(path="/helloBean/path-variable/{name}")
	public HelloWorldBean hellowWorld(@PathVariable String name)
	{
		
		return new HelloWorldBean(String.format("Hello World,%s ", name));
	}
	
	@GetMapping(path="/hello-world-internationalized")
	public String hellowWorldInternationalization(@RequestHeader(name="Accept-Language",required=false) Locale locale) 
	{
		
	return  messageSource.getMessage("good.morning.message", null,"Default Message",LocaleContextHolder.getLocale()  );
	}

}
 