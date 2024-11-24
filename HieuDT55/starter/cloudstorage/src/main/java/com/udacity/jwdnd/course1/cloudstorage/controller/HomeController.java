package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.udacity.jwdnd.course1.cloudstorage.model.FilesModel;
import com.udacity.jwdnd.course1.cloudstorage.model.ResponseFile;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesModel;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsModel;
import java.util.List;
import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    private FilesService filesService;

    @Autowired
    private NotesService notesService;

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/home")
    public String homeView(Authentication authentication, Model model) {
        SuperUser superUser = (SuperUser) authentication.getPrincipal();
        try {
            List<ResponseFile> files = filesService.getAllFiles(superUser.getUserId());
            model.addAttribute("files", files);

            List<NotesModel> notes = notesService.getAllNotes(superUser.getUserId());
            model.addAttribute("notes", notes);

            List<CredentialsModel> credentials = credentialsService.getAllCredentials(superUser.getUserId());
            model.addAttribute("credentials", credentials);


        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("files", new ArrayList<>());
            model.addAttribute("notes", new ArrayList<>());
            model.addAttribute("credentials", new ArrayList<>());
        }
        return "home";
    }

}