package com.neconico.neconico.controller.users;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.users.*;
import com.neconico.neconico.service.store.StoreInfoService;
import com.neconico.neconico.Maker.StoreInfoMaker;
import com.neconico.neconico.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final StoreInfoService storeInfoService;

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        if(error != null & exception != null) {
            model.addAttribute("error", error);
            model.addAttribute("errorMessage", "아이디 혹은 암호가 올바르지 않습니다.");
        }
        return "users/login";
    }

    /**
     * 회원가입
     */
    @GetMapping("/user/new")
    public String joinView(Model model){
        model.addAttribute("userJoinForm", new UserJoinDto());
        return "users/join";
    }

    @PostMapping("/user/new")
    public String joinResult(@Valid @ModelAttribute("userJoinForm") UserJoinDto userJoinDto,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "users/join";
        }

        Long userId = userService.joinUser(userJoinDto);
        storeInfoService.createStoreInfo(StoreInfoMaker.createStoreInfoDtoByUserId(userId));
        return "users/join_success";
    }

    /**
     * 아이디 찾기
     */
    @GetMapping("/user/find/id")
    public String findId(Model model) {
        model.addAttribute("userFindIdForm", new UserFindAccountIdDto());
        return "users/find_id";
    }

    @PostMapping("/user/find/id")
    public String findIdResult(@Valid @ModelAttribute("userFindIdForm") UserFindAccountIdDto findAccountIdDto,
                               BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "users/find_id";
        }

        UserAccountIdDto accountIdDto = userService.findAccountIdByNameAndEmail(findAccountIdDto);

        if(accountIdDto != null) {
            model.addAttribute("result", true);
            model.addAttribute("findAccountId", accountIdDto.getAccountId());
            return "users/find_id_result";
        }
        model.addAttribute("result", false);
        return "users/find_id_result";
    }

    /**
     * 비밀번호 찾기
     */
    @GetMapping("/user/find/pw")
    public String findPw(Model model) {
        model.addAttribute("userFindPwForm", new UserFindAccountPwDto());
        return "users/find_pw";
    }

    @PostMapping("/user/find/pw")
    public String findPwResult(@Valid @ModelAttribute("userFindPwForm") UserFindAccountPwDto findAccountPwDto,
                               BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "users/find_pw";
        }

        UserAccountIdDto accountIdDto = userService.findAccountPwByAccountIdAndPhoneNumAndEmail(findAccountPwDto);

        if(accountIdDto != null) {
            model.addAttribute("accountIdDto", accountIdDto);
            return "users/modify_pw";
        }
        return "users/find_pw_fail";
    }

    @PostMapping("/user/modify/pw")
    public String changePw(@ModelAttribute(name = "accountId") String accountId,
                           @ModelAttribute(name = "accountPw") String accountPw) {

        userService.changeUserAccountPw(accountId, accountPw);

        return "redirect:/login";
    }

    //인증거부 시 보여줄 페이지
    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception", required = false) String exception, @LoginUser SessionUser sessionUser, Model model) {

        model.addAttribute("username", sessionUser.getAccountName());
        return "users/denied";
    }
}
