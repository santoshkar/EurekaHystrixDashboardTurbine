package com.sbi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/**
 * 
 * @author Santosh Kumar Kar
 *
 */
public class SbiConsumerMVCController {
	
	@RequestMapping("/")
	public String defaultPage(){
		return "defaultPage";
	}
	
}
