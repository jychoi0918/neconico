package com.neconico.neconico.restcontroller.item;

import com.neconico.neconico.dto.item.ItemQuestionResponseDto;
import com.neconico.neconico.dto.item.QuestionCommentResponseDto;
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
                                                      @RequestParam(name = "userId") Long userId,
                                                      @RequestParam("content") String content) {
        return itemQuestionService.createItemQuestion(itemId, userId, content);

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


    //QuestionReply
    @PostMapping("/questionComment/{questionId}/new")
    public QuestionCommentResponseDto createQuestionComment(@PathVariable(name = "questionId") Long itemId,
                                                            @RequestParam(name = "userId") Long userId,
                                                            @RequestParam("content") String content) {
        return itemQuestionService.createQuestionCommentId(itemId, userId, content);

    }

    @PutMapping("/questionComment/{questionCommentId}/edit")
    public QuestionCommentResponseDto updateQuestionComment(@PathVariable(name = "questionCommentId") Long questionCommentId,
                                                            @RequestParam("content") String content) {
        return itemQuestionService.modifyQuestionComment(questionCommentId, content);

    }

    @DeleteMapping("/questionComment/{questionCommentId}/delete")
    private void deleteQuestionComment(@PathVariable(name = "questionCommentId") Long questionCommentId) {
        itemQuestionService.deleteQuestionComment(questionCommentId);
    }

}
