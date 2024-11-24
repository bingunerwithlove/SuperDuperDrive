package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NotesModel;
import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.udacity.jwdnd.course1.cloudstorage.model.FilesModel;
import com.udacity.jwdnd.course1.cloudstorage.model.ResponseFile;

@Controller
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PostMapping("/notes")
    public String createOrUpdateNote(NotesModel note, Authentication authentication, Model model) {
        SuperUser superUser = (SuperUser) authentication.getPrincipal();
        System.out.println(superUser.getUserId());
        if (note.getNoteid() > 0) {
            notesService.updateNote(note);
        } else {
            notesService.addNote(note, superUser.getUserId());
        }
        return "redirect:/result?success";
    }

    @GetMapping("/notes/home")
    public String homeViewNotes(Authentication authentication, Model model) {
        SuperUser superUser = (SuperUser) authentication.getPrincipal();
        try {
//            List<ResponseFile> files = filesService.getAllFiles(superUser.getUserId());
//            model.addAttribute("files", files);
        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("files", new ArrayList<>());
        }
        return "home";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") int noteid) {
        if (noteid > 0) {
            notesService.deleteNote(noteid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
