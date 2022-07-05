package pz1.MIC.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDefinitionDto {

    Long id;
    String name;
    Long rarity;
    String itemType;
}
