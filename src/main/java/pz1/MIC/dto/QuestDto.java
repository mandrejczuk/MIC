package pz1.MIC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestDto {

    Long id;
    String name;
    Long exp;
    Long prize;
    Long duration;
    String description;
    float questRarity;
    LocalDateTime startDateTime;
}
