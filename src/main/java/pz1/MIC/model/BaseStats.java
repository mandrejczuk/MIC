package pz1.MIC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name ="base_stats")
public class BaseStats {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long agility;

    private Long strength;

    private Long intelligence;

    private Long health;

    private Long luck;

    private Long speed;

    private Long endurance;

    private float critChance;

    private Long critDamage;

    private Long damage;

    private Long armour;

    private float blockChance;

    private float evasionChance;

    private Long exp;

    private Long gold;

    private String name;

    @OneToOne(mappedBy = "baseStats")
    private ItemDefinition itemDefinition;

    @OneToOne(mappedBy = "baseStats")
    private MonsterDefinition monsterDefinition;

    @OneToOne(mappedBy = "baseStats")
    private Race race;

    @OneToOne(mappedBy = "baseStats")
    private Class classes;


}
