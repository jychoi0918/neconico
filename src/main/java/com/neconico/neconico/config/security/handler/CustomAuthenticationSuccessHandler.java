package com.neconico.neconico.config.security.handler;

import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.dto.users.UserInfoDto;
import com.neconico.neconico.mapper.users.UserMapper;
import com.neconico.neconico.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationSuccessHandler")
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        setDefaultTargetUrl("/"); //기본 URL 설정

        UserInfoDto userInfoDto = userService.findUserByAccountId((String) authentication.getPrincipal());

        request.getSession()
                .setAttribute("sessionUser", createSessionUser(userInfoDto));

        if(savedRequest != null) { //인증이 되기전 요청 URL이 존재 시
            String target = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, target);
        }else {
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }

    private SessionUser createSessionUser(UserInfoDto userInfoDto) {
        return SessionUser.builder()
                .userId(userInfoDto.getUserId())
                .accountId(userInfoDto.getAccountId())
                .accountName(userInfoDto.getAccountName())
                .authority(userInfoDto.getAuthority())
                .email(userInfoDto.getEmail())
                .build();
    }
}
