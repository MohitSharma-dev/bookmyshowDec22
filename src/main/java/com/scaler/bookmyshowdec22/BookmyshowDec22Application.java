package com.scaler.bookmyshowdec22;

import com.scaler.bookmyshowdec22.controllers.UserController;
import com.scaler.bookmyshowdec22.dto.SignUpRequestDTO;
import com.scaler.bookmyshowdec22.models.BaseModel;
import com.scaler.bookmyshowdec22.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookmyshowDec22Application implements CommandLineRunner {
    @Autowired
    private UserController userController;
    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setEmail("rahul.sharma2@scaler.com");
        signUpRequestDTO.setPassword("password");
        userController.signUp(signUpRequestDTO);
    }

    public static void main(String[] args) {
//        BaseModel bs = new BaseModel();
//        bs.getId();
        SpringApplication.run(BookmyshowDec22Application.class, args);
    }

}
