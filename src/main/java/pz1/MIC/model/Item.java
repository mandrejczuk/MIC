package pz1.MIC.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pz1.MIC.repository.ItemDefinitionRepository;
import pz1.MIC.service.ItemDefinitionService;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "items")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long priceBuy;

    private Long priceSell;

    private String imgRef;

    private Long SlotNum;

    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name = "item_definition_id",referencedColumnName = "id")
    private ItemDefinition itemDefinition;

    @ManyToOne
    @JoinColumn(name = "character_equipment_id", referencedColumnName = "id")
    private CharacterEquipment characterEquipment;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "stats_id", referencedColumnName = "id")
    private Stats stats;


}
