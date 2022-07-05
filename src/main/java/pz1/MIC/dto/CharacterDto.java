package pz1.MIC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pz1.MIC.controller.request.IdHolder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterDto {

    String img;
    IdHolder user;
    IdHolder race;
    IdHolder classes;
    IdHolder stats;
    IdHolder characterEquipment;
    int freePoints;
}
