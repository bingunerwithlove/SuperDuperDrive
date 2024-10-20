package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @PostMapping("/credentials")
    public String saveOrUpdateCredentials(Authentication authentication, Credentials credential) {

    }

    @GetMapping("/credentials/delete")
    public String deleteNote(@RequestParam("id") int credentialid) {

    }
}
