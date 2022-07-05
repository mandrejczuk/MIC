package pz1.MIC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name ="items_definition")
public class ItemDefinition {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Long rarity;

    private String itemType;

    @OneToMany(mappedBy = "itemDefinition", cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "base_stats_id", referencedColumnName = "id")
    private BaseStats baseStats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "base_buffs_id", referencedColumnName = "id")
    private BaseBuffs baseBuffs;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class classes;

}
