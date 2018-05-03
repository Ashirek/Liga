package com.sieczka.controller;


import com.sieczka.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
public class RegistrationController {

    @Autowired
    private CustomUserDetailsService userDetailsService;





@RequestMapping(value = "/register", method = RequestMethod.POST )
    public ResponseEntity<?> createUser(@RequestBody UsersCreator usersCreator) {

    String answer;
    answer = userDetailsService.createUser(usersCreator.username, usersCreator.password, usersCreator.firstName, usersCreator.lastName, usersCreator.email);

    Map<String, String> result = new HashMap<>();
    result.put("result", answer);
    return ResponseEntity.accepted().body(result);
    }




    static class UsersCreator{
        public String username;
        public String password;
        public String firstName;
        public String lastName;
        public String email;
    }
}
