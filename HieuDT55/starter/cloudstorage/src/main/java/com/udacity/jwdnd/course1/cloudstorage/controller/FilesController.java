package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Controller
public class FilesController {

    @Autowired
    private FilesService filesService;

    @PostMapping("/files")
    public String saveFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        System.out.println(authentication);
        System.out.println("authenticationnull");
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login?error";
        }
        SuperUser superUser = (SuperUser) authentication.getPrincipal();
        if (fileUpload.isEmpty()) {
            return "redirect:/result?error";
        }
        filesService.addFile(fileUpload, superUser.getUserId());
        return "redirect:/result?success";
    }

    @GetMapping("/files/delete")
    public String deleteNote(@RequestParam("id") int fileid) {
        if (fileid > 0) {
            filesService.deleteFile(fileid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
