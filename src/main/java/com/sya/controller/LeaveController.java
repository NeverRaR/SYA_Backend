package com.sya.controller;

import com.sya.common.Pagination;
import com.sya.model.User;
import com.sya.request.LeaveManagementRequest;
import com.sya.request.LeaveRequset;
import com.sya.request.LeaveUpdateRequest;
import com.sya.service.AuthenticationService;
import com.sya.service.LeaveService;
import com.sya.view.LeaveItem;
import com.sya.view.LeaveItemsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Leave")
@RequiredArgsConstructor
public class LeaveController {

    private final AuthenticationService authenticationService;

    private final LeaveService leaveService;

    @PostMapping("RequestRest")
    @ResponseBody
    public LeaveItem createLeave(
            @RequestBody LeaveRequset leaveRequset,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ){
        User user = authenticationService.getUser(sessionId);
        if (user == null){
            return null;
        }

        return leaveService.createLeave(leaveRequset, user);
    }

    @PostMapping("ProViewLeaves")
    @ResponseBody
    public LeaveItemsPage proViewLeaves(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ){
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        if (user.getRole()==1){
            return null;
        }

        return leaveService.proViewLeaves(pagination, user);
    }

    @PostMapping("ProManageLeave")
    @ResponseBody
    public LeaveItem proManageLeave(
            @RequestBody LeaveManagementRequest leaveManagementRequest,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ){
        //FIXME: 此处应该是教师鉴权
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        if (user.getRole()==1){
            return null;
        }

        return leaveService.teacherUpdateLeave(leaveManagementRequest);
    }

    @PostMapping("ViewLeave")
    @ResponseBody
    public LeaveItemsPage viewLeaves(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ){
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        return leaveService.viewLeaves(pagination, user);
    }

    @PostMapping("UpdateLeave")
    @ResponseBody
    public LeaveItem updateLeave(
            @RequestBody LeaveUpdateRequest leaveUpdateRequest,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ){
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        return leaveService.updateLeave(leaveUpdateRequest,user);
    }

    @PostMapping("DeleteLeave")
    @ResponseBody
    public Integer deleteLeave(
            @RequestBody LeaveManagementRequest leaveManagementRequest,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ){
        User user = authenticationService.getUser(sessionId);
        if (user == null) {
            return null;
        }

        return leaveService.deleteLeave(leaveManagementRequest,user);
    }
}
