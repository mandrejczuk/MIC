package pz1.MIC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterListDto {

    Long id;
    String userName;
    int freePoints;
    String classesName;
    String raceName;
}
