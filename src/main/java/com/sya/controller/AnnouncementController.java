package com.sya.controller;

import com.sya.common.Pagination;
import com.sya.model.User;
import com.sya.request.AnnouncementIdDto;
import com.sya.request.CreateAnnouncementDto;
import com.sya.service.AnnouncementService;
import com.sya.service.AuthenticationService;
import com.sya.view.AnnouncementItem;
import com.sya.view.AnnouncementItemsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Announce")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    private final AuthenticationService authenticationService;

    @PostMapping("CreateAnnounce")
    @ResponseBody
    public Integer createAnnouncement(
            @RequestBody CreateAnnouncementDto createAnnouncementDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);

        // 如果没有该用户或用户登录过期或角色不是管理员
        if (user == null || user.getRole() != 0) {
            return -1;
        }

        return announcementService.createAnnouncement(createAnnouncementDto, user);
    }

    @PostMapping("GetAnnounce")
    @ResponseBody
    public AnnouncementItemsPage getAnnouncement(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);

        if (user == null) {
            return null;
        }

        return announcementService.getAnnouncement(pagination, user);
    }

    @PostMapping("GetAnnounceContent")
    @ResponseBody
    public AnnouncementItem getAnnouncementContent(
            @RequestBody AnnouncementIdDto announcementIdDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);

        if (user == null) {
            return null;
        }

        return announcementService.getAnnouncementContent(announcementIdDto, user);
    }

    @PostMapping("DeleteAnnounce")
    @ResponseBody
    public Integer deleteAnnouncement(
            @RequestBody AnnouncementIdDto announcementIdDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);

        if (user == null) {
            return 0;
        }

        return announcementService.deleteAnnouncement(announcementIdDto, user);
    }

    @PostMapping("DeleteAnnounceAll")
    @ResponseBody
    public Integer deleteAllAnnouncement(
            @RequestBody AnnouncementIdDto announcementIdDto,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);

        if (user == null || user.getRole() != 0) {
            return 0;
        }

        return announcementService.deleteAllAnnouncement(announcementIdDto, user);
    }

    @PostMapping("GetSendAnnounce")
    @ResponseBody
    public AnnouncementItemsPage getSendAnnouncement(
            @RequestBody Pagination pagination,
            @CookieValue(value = "sessionId", defaultValue = "noSession") String sessionId
    ) {
        User user = authenticationService.getUser(sessionId);

        if (user == null || user.getRole() != 0) {
            return null;
        }

        return announcementService.getSendAnnouncement(pagination, user);
    }
}
