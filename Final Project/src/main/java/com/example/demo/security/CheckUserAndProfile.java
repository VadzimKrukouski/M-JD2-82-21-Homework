package com.example.demo.security;

import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IUserService;
import org.springframework.stereotype.Component;

@Component
public class CheckUserAndProfile {
    private final UserHolder userHolder;
    private final IProfileService profileService;
    private final IUserService userService;

    public CheckUserAndProfile(UserHolder userHolder, IProfileService profileService, IUserService userService) {
        this.userHolder = userHolder;
        this.profileService = profileService;
        this.userService = userService;
    }

    public boolean checkProfileId(long idProfile){
        String login = userHolder.getAuthentication().getName();
        User user = userService.findUserByLogin(login);
        Profile profile = profileService.getById(idProfile);
        if (profile==null){
            throw new IllegalArgumentException("Profile is not found");
        }
        return user.getId()==profile.getUser().getId();
    }
}
