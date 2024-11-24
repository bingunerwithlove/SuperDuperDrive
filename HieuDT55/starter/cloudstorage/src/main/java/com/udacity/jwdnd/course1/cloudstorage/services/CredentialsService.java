package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsModel;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    @Autowired
    private CredentialsMapper credentialsMapper;

    @Autowired
    private UserService userService;

    public List<CredentialsModel> getAllCredentials(int userid) throws Exception {
        List<CredentialsModel> credentials = credentialsMapper.findByUserId(userid);
        if (credentials == null) {
            throw new Exception("No credentials found");
        }
        return credentials;
    }

    public void addCredential(CredentialsModel credential, int userid) {
        if (userService.findUserById(userid) == null) {
            throw new IllegalArgumentException("User does not exist, please check again");
        }
        credential.setUserid(userid);
        credentialsMapper.insertCredential(credential);
    }

    public void updateCredential(CredentialsModel credential) {
        credentialsMapper.updateCredential(credential);
    }

    public void deleteCredential(int credentialid) {
        credentialsMapper.deleteCredential(credentialid);
    }
}
