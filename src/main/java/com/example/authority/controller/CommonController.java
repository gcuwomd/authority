package com.example.authority.controller;

import com.example.authority.Service.CommonService;
import com.example.authority.util.OssUtil;
import com.example.authority.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class CommonController {
    @Autowired
    CommonService commonService;
    @Autowired
    OssUtil ossUtil;

    @GetMapping("/auth/accessible/website")
    ResultUtil getAccessibleWebsite(@RequestParam String id) {


        return ResultUtil.sucess(commonService.getAccessibleWebsite(id));

    }
    @PostMapping(value = "/auth/photo", consumes = "multipart/form-data")
    public synchronized ResultUtil descriptPhoto(@RequestParam("file") MultipartFile image) throws IOException {
        String fileName = image.getOriginalFilename();
        System.out.println(fileName);
        if (!(fileName != null && (fileName.toLowerCase().endsWith(".jpg") ||
                fileName.toLowerCase().endsWith(".jpeg") ||
                fileName.toLowerCase().endsWith(".png") ||
                fileName.toLowerCase().endsWith(".gif") ||
                fileName.toLowerCase().endsWith(".bmp")))) {
            return new ResultUtil(400, "只能上传图片", null);

        }
        return ResultUtil.sucess(ossUtil.put(image));
    }
    @DeleteMapping("/auth/photo")
    ResultUtil deleteImg(String key)  {
        URI uri= null;
        try {
            uri = new URI(key);
        } catch (URISyntaxException e) {

            return  new ResultUtil(403,"URL错误",null);
        }
        ossUtil.delete(uri.getPath());
        return ResultUtil.sucess();
    }
}
