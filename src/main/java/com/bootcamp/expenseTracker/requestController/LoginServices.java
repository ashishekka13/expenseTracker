package com.bootcamp.expenseTracker.requestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class LoginServices {

    private HashMap<String,AuthToken> tokens = new HashMap<String,AuthToken >();;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    private UserRepository users;

    public void setToken(AuthToken token){
        tokens.put(token.getId(),token);
    }
    public  AuthToken getToken(String id){
        AuthToken token=tokens.get(id);
        if (token.isValid())
            return token;
        return new AuthToken();
    }

    public String generatePassphrase(){
        int targetStringLength = 64;
        Random random = new Random();
        String generatedString = random.ints(48, 123)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public AuthToken verifyCredentials(User user){
        System.out.println(user.getUsername() );

        if (  encoder.matches(user.getPassword(),users.findById(user.getUsername()).get().getPassword())  ){
            return new AuthToken(user.getUsername(),generatePassphrase());
        }

        return  new AuthToken();
    }

    /*
        This section is an add-on for the OTP step.
     */
    public String generateOtp(AuthToken token){
        if (tokens.get(token.getId()).equals(token)) {
            byte[] passphrase = new byte[6];
            new SecureRandom().nextBytes(passphrase);
            return new String(passphrase, Charset.forName("UTF-8"));
        }
        return "";
    }

    public AuthToken verifyOTP(AuthToken token, String OTP){
        if (tokens.get(token.getId()).equals(token))
            return new AuthToken(token.getId(),generatePassphrase());
        return  new AuthToken();
    }
    /*
        OTP verification Step ends here.
     */

    public String encrypt(String password){
        return encoder.encode(password);
    }

    public boolean newUserRegistration(User user){
        if (users.existsById(user.getUsername())) {
            return false;
        }
        user.setPassword(encrypt(user.getPassword()));
        users.save(user);
        return true;
    }

    public AuthToken updatePassword(User user, AuthToken token){
        if(getToken(user.getUsername()).getKey().equals(token.getKey())) {
            User userToUpdate = users.findById(token.getId()).get();
            userToUpdate.setPassword(encrypt(user.getPassword()));
            users.save(userToUpdate);
            return new AuthToken(user.getUsername(), generatePassphrase());
        }
        return new AuthToken();
    }





}
