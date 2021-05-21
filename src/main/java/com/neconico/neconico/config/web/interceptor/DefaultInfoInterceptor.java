package com.neconico.neconico.config.web.interceptor;

import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.dto.users.UserInfoDto;
import com.neconico.neconico.service.category.CategoryService;
import com.neconico.neconico.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
public class DefaultInfoInterceptor implements HandlerInterceptor {

    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //자동로그인 정보 등록
       if(authentication instanceof RememberMeAuthenticationToken) {
            if(session.getAttribute("sessionUser") == null) {
                UserInfoDto userInfoDto = userService.findUserByAccountId(authentication.getName());
                session.setAttribute("sessionUser", createSessionUser(userInfoDto));
            }
        }

        if(session.getAttribute("categoryList") == null) {
            List<CategoryInfoDto> categoryInfoAll = categoryService.findCategoryInfoAll();
            session.setAttribute("categoryList", categoryInfoAll);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
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
