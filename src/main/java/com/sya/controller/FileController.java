package com.sya.controller;

import com.sya.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path="/File")
public class FileController {

    @Autowired
    OSSService ossService;

    @PostMapping(path = "/UploadFile") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestBody MultipartFile file) {
        return ossService.uploadFile(file);
    }

}
