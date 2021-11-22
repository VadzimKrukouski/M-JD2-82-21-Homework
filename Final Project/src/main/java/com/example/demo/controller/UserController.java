package com.example.demo.controller;

import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserAuthDTO;
import com.example.demo.email.SendEmail;
import com.example.demo.model.User;
import com.example.demo.model.api.EStatus;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IUserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping()
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

    @PostMapping("/register")
    public String login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "Error"; //тут должен быть возврат на форму заполнения и с помощью Thymeleaf вывести ошибки
        }
        User user = new User();
        user.setLogin(loginDTO.getLogin());
        user.setPassword(loginDTO.getPassword());
        //переделать, временный вариант
        userService.saveRegister(user);

        return "Ok";
    }

    @PostMapping("/auth")
    public String auth(@RequestBody @Valid UserAuthDTO userAuthDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "Error"; //тут должен быть возврат на форму заполнения и с помощью Thymeleaf вывести ошибки
        }
        User user = userService.findByLoginAndPassword(userAuthDTO.getLogin(), userAuthDTO.getPassword());
        if (user != null) {
            user.setStatus(EStatus.ACTIVE);
            user.setName(userAuthDTO.getName());
            userService.authUser(user);

            sendEmail.send("Registration in FoodApp", "You success registration!", user.getLogin());

            return jwtProvider.generateToken(user.getLogin());
        } else {
            return "User not found";
        }
    }

    @PostMapping("/personalData")
    public String savePersonalData(@RequestBody UserAuthDTO userAuthDTO) {
        profileService.save(userAuthDTO);
        return "Successfully save personal data";
    }

    //убрать 48 строку, сделать отдельный урл
}
