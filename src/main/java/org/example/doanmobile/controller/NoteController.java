package org.example.doanmobile.controller;

import org.example.doanmobile.model.NoteDto;
import org.example.doanmobile.security.UserPrincipal;
import org.example.doanmobile.service.inf.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    private INoteService noteService;

    @GetMapping("/user/notes")
    public List<NoteDto> getAllNote(@AuthenticationPrincipal UserPrincipal principal) {
        return noteService.findAllByUserId(principal.getUserId());
    }

    @GetMapping("/user/note/{noteId}")
    public Optional<NoteDto> getNoteById(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long noteId) {
        return noteService.findByUserIdAndId(principal.getUserId(), noteId);
    }

    @PostMapping("/user/note")
    public ResponseEntity<String> addNote(@AuthenticationPrincipal UserPrincipal principal, @RequestBody NoteDto noteDto) {

        if (noteService.save(noteDto,principal.getUsername()) == true) {
            return new ResponseEntity<>("Item added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add item to the database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/note/{noteId}")
    public ResponseEntity<String> updateNote(@RequestBody NoteDto noteDto, @PathVariable Long noteId) {

        if (noteService.update(noteDto, noteId) == true) {
            return new ResponseEntity<>("Item update successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update item to the database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/note/{noteId}")
    public void deleteNote(@PathVariable Long noteId) {
        noteService.delete(noteId);
    }
    @GetMapping("/user/notes/search")
    public List<NoteDto> searchNote(@AuthenticationPrincipal UserPrincipal principal,@RequestParam String title) {
        return noteService.searchNote(title, principal.getUserId());
    }

}
