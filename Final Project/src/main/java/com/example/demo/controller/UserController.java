package com.example.demo.controller;

import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserAuthDTO;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserController {
    private final IUserService userService;
    private final IProfileService profileService;
    private final JwtProvider jwtProvider;


    public UserController(IUserService userService, IProfileService profileService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.profileService = profileService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public String login(@RequestBody LoginDTO loginDTO) {
        User user = new User();
        user.setLogin(loginDTO.getLogin());
        user.setPassword(loginDTO.getPassword());
        //переделать, временный вариант
        userService.saveRegister(user);

        return "Ok";
    }

    @PostMapping("/auth")
    public String auth(@RequestBody UserAuthDTO userAuthDTO){
        User user = userService.findByLoginAndPassword(userAuthDTO.getLogin(), userAuthDTO.getPassword());
        if (user!=null){
            userService.authUser(userAuthDTO);
            profileService.save(userAuthDTO);

            return jwtProvider.generateToken(user.getLogin());
        }else {
            return "User not found";
        }
    }
}
