package com.bootcamp.expenseTracker.requestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
        return "Hello. You are home. Please stay here";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verifyCredentials")
    public AuthToken verifyCredentials(@RequestBody User user){
        AuthToken token =loginService.verifyCredentials(user);
        loginService.setToken(token);
        return token;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getOTP")
    public Object getOTP(@RequestBody Object request){
        System.out.println(request);
        return request;

    }
/*
    @RequestMapping(method = RequestMethod.POST, value = "/verifyOTP")
    public  AuthToken verifyOTP(@RequestBody Object request){
        return loginService.verifyOTP(request.getClass());
    }
*/

/*  Doubt
    @RequestMapping(method = RequestMethod.POST,value = "/updatePassword")
    public AuthToken updatePassword(@RequestBody User user){
        return loginService.updatePassword(user,token);
    }
*/
    @RequestMapping(method = RequestMethod.POST, value= "/registerUser")
    public String createUser(@RequestBody User user){
        if(loginService.newUserRegistration(user)){

            return "SUCCESS";
        }
        else
            return "FAILED";
    }




    @RequestMapping(method = RequestMethod.POST, value = "/updatePassword")
    public AuthToken updatePassword(@RequestBody User user) {
        AuthToken token = loginService.updatePassword(user,user.getToken());
        loginService.setToken(token);
        return token;
    }




}
