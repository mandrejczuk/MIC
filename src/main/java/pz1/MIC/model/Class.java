package pz1.MIC.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name ="classes")
public class Class {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstWeaponType;

    private String secondWeaponType;

    private String armourType;

    private String name;

    @OneToMany(mappedBy = "classes")
    private List<Character> characters;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "base_buffs_id", referencedColumnName = "id")
    private BaseBuffs baseBuffs;

    @OneToOne
    @JoinColumn(name = "base_stats_id", referencedColumnName = "id")
    private BaseStats baseStats;

    @OneToMany(mappedBy = "classes")
    private List<ItemDefinition> itemDefinitions;

}
