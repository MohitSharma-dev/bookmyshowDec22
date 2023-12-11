package com.scaler.bookmyshowdec22.services;

import com.scaler.bookmyshowdec22.models.User;
import com.scaler.bookmyshowdec22.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User signUp(String email, String password){
        // validate if user already exists
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);
        user.setBookings(new ArrayList<>());

        user = userRepository.save(user);
        return user;
    }

    public User logIn(String email, String password){
        // 1. check if email is registered
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new RuntimeException("No user with the given Email!");
        }
        // 2. validate the password
        User user = userOptional.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password,user.getPassword())){
            return user;
        }
        throw new RuntimeException("Invalid password!");

    }
}

// password === 521445678521
// p -> 5
// a -> 21
