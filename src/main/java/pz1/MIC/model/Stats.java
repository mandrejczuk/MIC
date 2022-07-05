package pz1.MIC.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name ="stats")
@Builder
public class Stats {

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

    @OneToOne(mappedBy = "stats")
    private Item item;

    @OneToOne(mappedBy = "stats", cascade = CascadeType.ALL)
    private Character character;

    @OneToOne(mappedBy = "stats")
    private Monster monster;
}
