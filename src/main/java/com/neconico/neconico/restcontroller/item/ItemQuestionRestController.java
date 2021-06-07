package com.neconico.neconico.restcontroller.item;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.item.ItemQuestionResponseDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.service.item.ItemQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ItemQuestionRestController {

    private final ItemQuestionService itemQuestionService;

    //Question
    @PostMapping("/question/{itemId}/new")
    public ItemQuestionResponseDto createItemQuestion(@PathVariable(name = "itemId") Long itemId,
                                                      @LoginUser SessionUser user,
                                                      @RequestParam("content") String content) {
        return itemQuestionService.createItemQuestion(itemId, user.getUserId(), content);

    }

    @PutMapping("/question/{questionId}/edit")
    public ItemQuestionResponseDto updateItemQuestion(@PathVariable(name = "questionId") Long questionId,
                                                      @RequestParam("content") String content) {
        return itemQuestionService.modifyItemQuestion(questionId, content);

    }

    @DeleteMapping("/question/{questionId}/delete")
    private void deleteItemQuestion(@PathVariable(name = "questionId") Long questionId) {
        itemQuestionService.deleteItemQuestion(questionId);
    }

}
