package com.neconico.neconico.restcontroller.users;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.dto.users.UserInfoDto;
import com.neconico.neconico.service.email.EmailService;
import com.neconico.neconico.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UsersRestController {

    private final UserService userService;
    private final EmailService emailService;

    @GetMapping("/user/{userId}/check")
    public ResponseEntity<UserInfoDto> confirmAccountIdDuplicate(@PathVariable("userId") String checkId) {
        UserInfoDto userInfoDto = userService.findUserByAccountId(checkId);
        return new ResponseEntity<>(userInfoDto, HttpStatus.OK);
    }

    @PostMapping("/user/email/send")
    public ResponseEntity<Long> sendEmail(@RequestParam("emailAddress") String emailAddress) {
        try {
            Long emailId = emailService.sendAuthorMailTemplate(emailAddress,"/email/email_form.html", 6);
            return new ResponseEntity<>(emailId, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/email/{authorNumber}/check")
    public ResponseEntity<Long> checkAuthorNumber(@PathVariable("authorNumber") String authorNumber) {
        Long result = emailService.checkExistNumber(authorNumber);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/user/email/{emailId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthorNumber(@PathVariable("emailId") Long emailId) {
        emailService.deleteAuthorNumber(emailId);
    }

    //아이템 등록시 내 주소 검색
    @GetMapping("/user/find/address")
    public ResponseEntity<UserInfoDto> findUserAddress(@LoginUser SessionUser sessionUser) {
        UserInfoDto userInfoDto = userService.findUserByAccountId(sessionUser.getAccountId());
        return new ResponseEntity<>(userInfoDto, HttpStatus.OK);
    }

    //이메일 증복 체크
    @GetMapping("/user/check/email/{email}")
    public ResponseEntity<Boolean> checkEmailDuplication(@PathVariable("email") String email) {
        if(userService.findUserByEmail(email) == null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
