package com.neconico.neconico.controller.item;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.process.S3FileProcess;
import com.neconico.neconico.service.category.CategoryService;
import com.neconico.neconico.service.file.FileService;
import com.neconico.neconico.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final CategoryService categoryService;
    private final FileService fileService;

    @GetMapping("/item/new")
    public String createItemPage(Model model) {
        List<CategoryInfoDto> categoryInfoAll = categoryService.findCategoryInfoAll();
        model.addAttribute("itemFrom", new ItemInfoDto());
        model.addAttribute("categoryList", categoryInfoAll);
        return "item/new_item";
    }

    @PostMapping("/item/new")
    @ResponseBody
    public ResponseEntity<ItemInfoDto> createItemResult(@RequestParam("files") MultipartFile[] multipartFiles,
                                                        @ModelAttribute("itemFrom") ItemInfoDto itemInfoDto,
                                                        @RequestParam("subId") Long subId,
                                                        @LoginUser SessionUser sessionUser) throws Exception {
        if(multipartFiles.length == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        fileService.setFileProcess(new S3FileProcess(FilePolicy.ITEM));
        FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFiles);

        itemInfoDto.setUserId(sessionUser.getUserId()); //작성자 userId 등록
        itemService.insertItem(fileResultInfoDto, subId, itemInfoDto);

        return new ResponseEntity<>(itemInfoDto, HttpStatus.CREATED);
    }
}
