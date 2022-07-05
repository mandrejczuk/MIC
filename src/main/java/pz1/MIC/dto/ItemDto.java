package pz1.MIC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDto {

    Long id;
    Long priceSell;
    Long priceBuy;
    String imgRef;
    Long SlotNum;
    Long characterEquipmentId;
    ItemDefinitionDto itemDefinition;
    StatsDto stats;
}
