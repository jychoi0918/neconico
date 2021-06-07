package com.neconico.neconico.controller.admin.user;

import com.neconico.neconico.paging.Criteria;
import com.neconico.neconico.paging.Pagination;
import com.neconico.neconico.service.admin.user.UsersAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class UserAdminController {

    private final UsersAdminService usersAdminService;

    //일반 회원 목록 조회
    @GetMapping("/admin/users/list")
    public String userList(Model model, Criteria cri) throws Exception{

    model.addAttribute("userList",usersAdminService.selectUserList(cri));
    model.addAttribute("paging",new Pagination(cri, Math.toIntExact(usersAdminService.countUserList("ROLE_USER")),10));

    return "admin/users/users_list";
    }

    //관리자 회원 목록 조회
    @GetMapping("/admin/users/administrator")
    public String userListAdmin(Model model, Criteria cri) throws Exception{

        model.addAttribute("userList",usersAdminService.selectAdminList(cri));
        model.addAttribute("paging",new Pagination(cri, Math.toIntExact(usersAdminService.countUserList("ROLE_ADMIN")),10));

        return "admin/users/users_administrator";
    }

    //그룹별 회원 조회
    @GetMapping("/admin/users/group")
    public String userListByGroup(Model model) {

        model.addAttribute("userListByAge",usersAdminService.selectUserListByAge());
        model.addAttribute("userListByGender",usersAdminService.selectUserListByGender());
        model.addAttribute("userListByLocation",usersAdminService.selectUserListByLocation());

        return "admin/users/users_group";
    }

    //가입날짜별 회원 조회
    @GetMapping("/admin/users/registered")
    public String userListByRegistered(Model model) {

        model.addAttribute("adminUserListByRegistered",usersAdminService.selectUserListByRegistered());

        return "admin/users/users_registered";
    }

}
