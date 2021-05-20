package com.neconico.neconico.restcontroller.users;

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
    public ResponseEntity<Long> sendEmail(@RequestParam("emailAddress") String emailAddress) throws Exception{
        Long emailId = emailService.sendAuthorNumberMail(emailAddress, 6);

        return new ResponseEntity<>(emailId, HttpStatus.OK);
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

}
