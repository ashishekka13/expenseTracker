package com.bootcamp.expenseTracker.requestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

@Service
public class LoginServices {

    private List<AuthToken> tokens;
    @Autowired
    private UserRepository users;

    public void loginService(){
        tokens=new LinkedList<AuthToken>();
    }
    public String generatePassphrase(){
        return "Wwd7BD8h93nd83DHVN2dS2s2sf3js";
    }

    public AuthToken verifyCredentials(User user){
//        users.findAll().forEach( System.out::println);
        System.out.println(user.getUsername() );
        if ( users.findById(user.getUsername()).get().getPassword().equals(user.getPassword()) ){
            return new AuthToken(user.getUsername(),generatePassphrase());
        }
        return  new AuthToken();
    }

    public String generateOtp(AuthToken token){
        return "123456";
    }

    public AuthToken verifyOTP(String OTP){
        return new AuthToken("admin",generatePassphrase());
    }

    public String encrypt(String plainPassword){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(plainPassword);
    }

}
