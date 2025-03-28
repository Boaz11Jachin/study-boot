package org.codenova.start.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Comment {
    private int id;
    private String isoCode;
    private String body;
    private LocalDateTime writedAt;
}
