package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class UserController {
    private final IUserService userService;
    private final IProfileService profileService;

    public UserController(IUserService userService, IProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @PostMapping("user")
    public String login(@RequestBody LoginDTO loginDTO) {
        String token = getJWTToken(loginDTO.getLogin());
        User user = new User();
        user.setLogin(loginDTO.getLogin());
        user.setPassword(loginDTO.getPassword());

        //переделать, временный вариант
        Profile profile = new Profile();
        userService.save(user);
        profileService.save(profile);

        return token;

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();

        return "Bearer " + token;
    }
}
