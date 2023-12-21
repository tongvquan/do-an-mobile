package org.example.doanmobile.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDto {
    private Long id;
    private String title;
    private String content;
}
