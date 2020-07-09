package com.bootcamp.expenseTracker.requestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHandler {

    @Autowired
    private LoginServices loginService;

    @RequestMapping(value = "/home")
    public String Home(){
        return "Hello. You are home";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verifyCredentials")
    public AuthToken verifyCredentials(@RequestBody User user){
        AuthToken token =loginService.verifyCredentials(user);
        return token;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getOTP")
    public Object getOTP(@RequestBody Object request){
        System.out.println(request);
        return request;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/verifyOTP")
    public  AuthToken verifyOTP(@RequestBody String OTP){
        return loginService.verifyOTP(OTP);
    }






}
