package pz1.MIC.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pz1.MIC.enums.ClassEnum;
import pz1.MIC.enums.RaceEnum;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterRequest {

    String img;
    IdHolder race;
    IdHolder classes;
    ClassEnum classEnum;
    RaceEnum raceEnum;
}
