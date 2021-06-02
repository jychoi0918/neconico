package com.neconico.neconico.controller.item;

import com.neconico.neconico.config.web.LoginUser;
import com.neconico.neconico.dto.category.CategoryInfoDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardViewDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.process.S3FileProcess;
import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
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

    /**
     * 검색
     */
    @GetMapping("/item/search")
    public String searchItems(@ModelAttribute("searchText")SearchInfoDto searchInfoDto,
                               @ModelAttribute("currentPage") Criteria criteria,
                               Model model) {
        List<ItemCardViewDto> itemCardViewDtoList = itemService.searchItems(criteria, searchInfoDto);
        int totalContent = itemService.countTotalItems(searchInfoDto).intValue();

        model.addAttribute("search", searchInfoDto.getSearchText());
        model.addAttribute("itemCardList", itemCardViewDtoList);
        model.addAttribute("pagination", new Pagination(criteria, totalContent,5));
        return "item/sch_result";
    }

    @GetMapping("/item/search/category")
    public String searchItemsByCategoryId(@RequestParam("sub") Long subId,
                                          @RequestParam("name") String subName,
                                          @ModelAttribute("currentPage") Criteria criteria,
                                          Model model) {

        List<ItemCardViewDto> itemCardViewDtoList = itemService.searchItemsBySubCategoryId(criteria, subId);
        int totalContent = itemService.countTotalItemsBySubCategoryId(subId).intValue();

        model.addAttribute("subId", subId);
        model.addAttribute("subName", subName);
        model.addAttribute("itemCardList", itemCardViewDtoList);
        model.addAttribute("pagination", new Pagination(criteria, totalContent, 5));
        return "item/sch_category_result";
    }

    /**
     * 아이템 수정
     * 아이템 삭제
     */
    @GetMapping("/item/{itemId}/edit")
    public String updateItemPage(@PathVariable("itemId") Long itemId, Model model) {
        ItemInfoDto itemInfoDto = itemService.findItemByItemIdForUpdate(itemId);
        List<CategoryInfoDto> categoryInfoAll = categoryService.findCategoryInfoAll();

        model.addAttribute("itemForm", itemInfoDto);
        model.addAttribute("categoryList", categoryInfoAll);
        return "item/edit_item";
    }

    @PostMapping("/item/{itemId}/edit")
    @ResponseStatus(HttpStatus.OK)
    public void updateItemResult(@RequestParam(value = "files", required = false) MultipartFile[] multipartFiles,
                                 @ModelAttribute("itemFrom") ItemInfoDto itemInfoDto,
                                 @RequestParam("subId") Long categorySubId,
                                 @RequestParam("currentFiles") String[] currentFiles) throws Exception {

        fileService.setFileProcess(new S3FileProcess(FilePolicy.ITEM));

        itemInfoDto.setCategorySubId(categorySubId); // 카테고리 변경사항 적용

        //새로 등록한 파일이 있을경우
        if(multipartFiles != null) {
            FileResultInfoDto fileResultInfoDto = fileService.uploadFiles(multipartFiles);
            String deleteFileNames = itemService.changeItemInfo(fileResultInfoDto, currentFiles, itemInfoDto);
            if(!deleteFileNames.isEmpty()) {
                fileService.deleteFiles(deleteFileNames);
            }
        }else { // 새로 등록한 파일이 없을경우
            String deleteFileNames = itemService.changeItemInfo(null, currentFiles, itemInfoDto);

            if(!deleteFileNames.isEmpty()) {
                fileService.deleteFiles(deleteFileNames);
            }
        }
    }


    @DeleteMapping("/item/{itemId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        ItemInfoDto itemInfoDto = itemService.findItemByItemIdForUpdate(itemId);

        fileService.setFileProcess(new S3FileProcess(FilePolicy.ITEM));
        fileService.deleteFiles(itemInfoDto.getImgFileNames());
        itemService.removeItem(itemId);
    }
}
