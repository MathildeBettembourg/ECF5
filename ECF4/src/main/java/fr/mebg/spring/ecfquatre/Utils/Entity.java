package fr.mebg.spring.ecfquatre.Utils;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Entity {
    @Id
    private String id;

    private LocalDateTime DateCreation = LocalDateTime.now();
    private LocalDateTime DateModification = LocalDateTime.now();
}
