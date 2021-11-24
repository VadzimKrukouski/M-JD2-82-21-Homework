package com.example.demo.controller;

import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.dto.AuthDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.PersonalDataDTO;
import com.example.demo.email.SendEmail;
import com.example.demo.model.User;
import com.example.demo.model.api.ERole;
import com.example.demo.model.api.EStatus;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final IUserService userService;
    private final IProfileService profileService;
    private final JwtProvider jwtProvider;
    private final SendEmail sendEmail;


    public UserController(IUserService userService, IProfileService profileService, JwtProvider jwtProvider, SendEmail sendEmail) {
        this.userService = userService;
        this.profileService = profileService;
        this.jwtProvider = jwtProvider;
        this.sendEmail = sendEmail;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> userList = userService.getAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public String login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "Incorrect login or password!";
        }
        User userByLogin = userService.findUserByLogin(loginDTO.getLogin());
        if (userByLogin!=null){
            return "This user already exist";
        }

        User user = new User();
        user.setLogin(loginDTO.getLogin());
        user.setPassword(loginDTO.getPassword());
        userService.saveNewUser(user);

        sendEmail.send("Registration in FoodApp", "You success registration!", user.getLogin());

        return "You have successfully registered. An email has been sent to your mail to activate your account";
    }

    @PostMapping("/auth")
    public String auth(@RequestBody AuthDTO authDTO) {

        User user = userService.findByLoginAndPassword(authDTO.getLogin(), authDTO.getPassword());

        if (user != null) {
            if (user.getRole().equals(ERole.ROLE_ADMIN)){
                return jwtProvider.generateToken(user.getLogin());
            }
            user.setStatus(EStatus.ACTIVE);
            user.setName(authDTO.getName());
            userService.checkAndUpdateDataUser(user);

            return jwtProvider.generateToken(user.getLogin());
        } else {
            return "User not found";
        }
    }

    @PostMapping("/personalData")
    public String savePersonalData(@RequestBody @Valid PersonalDataDTO personalDataDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "Incorrect fields!";
        }
        profileService.save(personalDataDTO);
        return "Successfully save personal data";
    }
}
