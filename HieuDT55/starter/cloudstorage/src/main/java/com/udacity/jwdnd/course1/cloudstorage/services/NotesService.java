package com.udacity.jwdnd.course1.cloudstorage.services;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class NotesService {
    public List<String> getAllNotes() {
        return List.of("Note 1", "Note 2", "Note 3");
    }
}