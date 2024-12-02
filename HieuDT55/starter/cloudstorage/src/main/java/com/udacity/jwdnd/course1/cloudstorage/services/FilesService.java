package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FilesModel;
import com.udacity.jwdnd.course1.cloudstorage.model.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilesService {

    @Autowired
    private FilesMapper filesMapper;

    public ResponseFile getResponseFile(FilesModel file) {
        String encodedKey = Base64.getEncoder().encodeToString(file.getFiledata());
        String dataURL = "data:" + file.getContenttype() + ";base64," + encodedKey;
        return ResponseFile.builder().fileid(file.getFileid()).filename(file.getFilename()).dataURL(dataURL).build();
    }

    public List<ResponseFile> getAllFiles(int userid) throws Exception {
        List<FilesModel> files = filesMapper.findByUserId(userid);
        if (files == null) {
            throw new Exception();
        }
        return files.stream().map(this::getResponseFile).collect(Collectors.toList());
    }

    public void addFile(MultipartFile fileUpload, int userid) throws IOException {
        FilesModel file = new FilesModel();
        FilesModel isDuplicateFile = checkDuplicateFile(fileUpload.getOriginalFilename(), userid);
//        System.out.println("duplicate=" + isDuplicateFile);
        if (isDuplicateFile != null) {
            throw new IllegalArgumentException("duplicate");
        } else {
            try {
                file.setContenttype(fileUpload.getContentType());
                file.setFiledata(fileUpload.getBytes());
                file.setFilename(fileUpload.getOriginalFilename());
                file.setFilesize(Long.toString(fileUpload.getSize()));
                file.setUserid(userid);
            } catch (IOException e) {
                throw e;
            }
        }
        filesMapper.insertFile(file, userid);
    }

    public FilesModel checkDuplicateFile(String filename, int userid) {
        List<FilesModel> existingFiles = filesMapper.findByFileNameandUserId(filename, userid);
        if (existingFiles != null && !existingFiles.isEmpty()) {
            return existingFiles.get(0);
        }
        return null;
    }

    public void deleteFile(int fileid) {
        filesMapper.deleteFile(fileid);
    }
}