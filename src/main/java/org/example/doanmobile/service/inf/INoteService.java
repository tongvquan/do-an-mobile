package org.example.doanmobile.service.inf;

import org.example.doanmobile.entity.UserEntity;
import org.example.doanmobile.model.NoteDto;
import org.example.doanmobile.security.UserPrincipal;

import java.util.List;
import java.util.Optional;

public interface INoteService {
    List<NoteDto> findAllByUserId(Long userId);
    Optional<NoteDto> findByUserIdAndId(Long userId, Long noteId);
    Boolean save(NoteDto noteDto, String userName);

    Boolean update(NoteDto noteDto, Long noteId);

    void delete(Long noteId);
    List<NoteDto> searchNote(String title, Long userId);
}
