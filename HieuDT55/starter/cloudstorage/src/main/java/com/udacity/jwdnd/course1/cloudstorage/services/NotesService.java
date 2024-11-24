package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    @Autowired
    private NotesMapper notesMapper;

    public List<NotesModel> getAllNotes(int userid) throws Exception {
        List<NotesModel> notes = notesMapper.findByUserId(userid);
        if (notes == null) {
            throw new Exception("No notes found for user id: " + userid);
        }
        return notes;
    }

    public void addNote(NotesModel note, int userid) {
        note.setUserid(userid);

        notesMapper.insertNote(note);
    }



    public void updateNote(NotesModel note) {
        notesMapper.updateNote(note);
    }

    public void deleteNote(int noteid) {
        notesMapper.deleteNote(noteid);
    }
}
