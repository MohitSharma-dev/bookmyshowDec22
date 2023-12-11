package com.scaler.bookmyshowdec22.controllers;

import com.scaler.bookmyshowdec22.dto.ResponseStatus;
import com.scaler.bookmyshowdec22.dto.SignUpRequestDTO;
import com.scaler.bookmyshowdec22.dto.SignUpResponseDTO;
import com.scaler.bookmyshowdec22.models.User;
import com.scaler.bookmyshowdec22.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO){
        SignUpResponseDTO response = new SignUpResponseDTO();
        try {
            User user = userService.signUp(
                    signUpRequestDTO.getEmail(),
                    signUpRequestDTO.getPassword()
            );
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setUserId(user.getId());
        } catch (Exception e){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
