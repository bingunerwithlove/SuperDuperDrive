package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsModel;
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
    public String saveOrUpdateCredentials(Authentication authentication, CredentialsModel credential) {
        SuperUser superUser = (SuperUser) authentication.getPrincipal();
        if (credential.getCredentialid() > 0) {
            credentialsService.updateCredential(credential);
        } else {
            credentialsService.addCredential(credential, superUser.getUserId());
        }
        return "redirect:/result?success";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam("id") int credentialid) {
        if (credentialid > 0) {
            credentialsService.deleteCredential(credentialid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
