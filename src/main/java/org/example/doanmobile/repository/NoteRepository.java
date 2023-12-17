package org.example.doanmobile.repository;

import org.example.doanmobile.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findAllByUserId(Long userId);
    NoteEntity findByUserIdAndId(Long userId, Long noteId);

}
