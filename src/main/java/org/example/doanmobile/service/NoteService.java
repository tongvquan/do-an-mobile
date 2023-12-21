package org.example.doanmobile.service;

import org.example.doanmobile.entity.NoteEntity;
import org.example.doanmobile.entity.UserEntity;
import org.example.doanmobile.model.NoteDto;
import org.example.doanmobile.repository.NoteRepository;
import org.example.doanmobile.repository.UserRepository;
import org.example.doanmobile.service.inf.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements INoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<NoteDto> findAllByUserId(Long userId) {
        List<NoteDto> list = new ArrayList<>();
        for(NoteEntity noteEntity : noteRepository.findAllByUserId(userId)){
            NoteDto noteDto = new NoteDto();
            noteDto.setId(noteEntity.getId());
            noteDto.setTitle(noteEntity.getTitle());
            noteDto.setContent(noteEntity.getContent());
            list.add(noteDto);
        }
        return list;
    }

    @Override
    public Optional<NoteDto> findByUserIdAndId(Long userId, Long noteId) {
        NoteDto noteDto = new NoteDto();
        NoteEntity noteEntity = noteRepository.findByUserIdAndId(userId, noteId);
        if(noteEntity == null)  return Optional.empty();

        noteDto.setTitle(noteEntity.getTitle());
        noteDto.setContent(noteEntity.getContent());
        return Optional.of(noteDto);
    }

    @Override
    public Boolean save(NoteDto noteDto, String userName) {
        try {
            NoteEntity noteEntity = new NoteEntity();
            UserEntity user = userRepository.findByUserName(userName);
            noteEntity.setContent(noteDto.getContent());
            noteEntity.setTitle(noteDto.getTitle());
            noteEntity.setUser(user);
            noteEntity.setModifiedDate(new Time(System.currentTimeMillis()));
            noteRepository.save(noteEntity);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean update(NoteDto noteDto, Long noteId) {
        Optional<NoteEntity> optionalNoteEntity = noteRepository.findById(noteId);
        if (optionalNoteEntity.isPresent()){
            NoteEntity existingNoteEntity = optionalNoteEntity.get();
            try {
                if(noteDto.getTitle() != null)
                    existingNoteEntity.setTitle(noteDto.getTitle());
                if(noteDto.getContent() != null)
                    existingNoteEntity.setContent(noteDto.getContent());
                existingNoteEntity.setModifiedDate(new Time(System.currentTimeMillis()));
                noteRepository.save(existingNoteEntity);
                return true;
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        else {
            return false;
        }

    }

    @Override
    public void delete(Long noteId) {
        Optional<NoteEntity> optionalNoteEntity = noteRepository.findById(noteId);
        if (!optionalNoteEntity.isPresent()){
           throw new RuntimeException("khong the tim thay "+ noteId+ " de xoa");
        }
        else {
            NoteEntity note = optionalNoteEntity.get();
            noteRepository.delete(note);
        }

    }

    @Override
    public List<NoteDto> searchNote(String title, Long userId) {
        List<NoteDto> list = new ArrayList<NoteDto>();
        for(NoteEntity noteEntity : noteRepository.findAllByUserId(userId)){
            if(noteEntity.getTitle().equalsIgnoreCase(title)){
                NoteDto noteDto = new NoteDto();
                noteDto.setId(noteEntity.getId());
                noteDto.setTitle(noteEntity.getTitle());
                noteDto.setContent(noteEntity.getContent());
                list.add(noteDto);
            }
        }
        return list;
    }

}
