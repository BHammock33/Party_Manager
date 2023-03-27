package com.partymanager.finalproject.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		String errorPage = "error";
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				errorPage = "error/404";
			}else if(statusCode == HttpStatus.FORBIDDEN.value()) {
				errorPage = "error/403";
			}else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				errorPage = "error/500";
			}else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				errorPage = "error/405";
			}
		}
		return errorPage;
	}
	@PostMapping("/error")
	public String handleErrorPost(HttpServletRequest request) {
		String errorPage = "error";
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				errorPage = "error/404";
			}else if(statusCode == HttpStatus.FORBIDDEN.value()) {
				errorPage = "error/403";
			}else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				errorPage = "error/500";
			}else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				errorPage = "error/405";
			}
		}
		return errorPage;
	}
	public String getErrorPath() {
		return "/error";
	}
}
