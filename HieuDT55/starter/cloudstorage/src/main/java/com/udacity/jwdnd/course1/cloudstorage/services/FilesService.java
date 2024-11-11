package com.udacity.jwdnd.course1.cloudstorage.services;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@Service
public class FilesService {
    public List<String> getAllFiles() {
        return List.of("File1.txt", "File2.png");
    }
}