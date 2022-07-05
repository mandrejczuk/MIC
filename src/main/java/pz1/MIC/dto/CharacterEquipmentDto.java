package pz1.MIC.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pz1.MIC.controller.request.IdHolder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterEquipmentDto {
    Long id;
    Long priceSell;
    Long priceBuy;
    String imgRef;
    Long SlotNum;
    ItemDefinitionDto itemDefinition;
    StatsDto stats;

}
