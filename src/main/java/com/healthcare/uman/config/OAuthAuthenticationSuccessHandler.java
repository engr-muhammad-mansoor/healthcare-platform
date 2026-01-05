package com.healthcare.uman.config;

import com.healthcare.uman.model.user.ProviderEnum;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepository userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);
        var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        User user = new User();
        user.setActive(true);
        user.setPassword("dummy");

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            user.setEmail(Objects.requireNonNull(oauthUser.getAttribute("email")).toString());
            user.setProfile(Objects.requireNonNull(oauthUser.getAttribute("picture")).toString());
            user.setFirstName(Objects.requireNonNull(oauthUser.getAttribute("name")).toString());
            user.setProvider(String.valueOf(ProviderEnum.GOOGLE));

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("facebook")) {
            user.setEmail(Objects.requireNonNull(oauthUser.getAttribute("email")).toString());
            user.setProfile(Objects.requireNonNull(oauthUser.getAttribute("picture")).toString());
            user.setFirstName(Objects.requireNonNull(oauthUser.getAttribute("name")).toString());
            user.setProvider(String.valueOf(ProviderEnum.FACEBOOK));

        } else {
            logger.info("OAuthAuthenticationSuccessHandler: Unknown provider");
        }
        User user2 = userRepo.findByEmail(user.getEmail());
        if (user2 == null) {
            userRepo.save(user);
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/home/getuser");
    }
}
