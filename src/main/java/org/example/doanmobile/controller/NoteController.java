//package org.example.doanmobile.controller;
//
//import org.example.doanmobile.model.NoteDto;
//import org.example.doanmobile.security.UserPrincipal;
//import org.example.doanmobile.service.inf.INoteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class NoteController {
//
//    @Autowired
//    private INoteService noteService;
//
//    @GetMapping("/user/notes")
//    public List<NoteDto> note(@AuthenticationPrincipal UserPrincipal principal) {
//        return noteService.findAllByUser(principal.getUserId());
//    }
//}
