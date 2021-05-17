package com.neconico.neconico.controller.users;

import com.neconico.neconico.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 로그인
     * @param error
     * @param exception
     * @param model
     * @return
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

}
