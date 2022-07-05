package pz1.MIC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name ="base_buffs")
public class BaseBuffs {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private Long agilityAmp;

    private Long strengthAmp;

    private Long intelligenceAmp;

    private Long healthAmp;

    private Long luckAmp;

    private Long speedAmp;

    private Long enduranceAmp;

    private float critChanceAmp;

    private Long critDamageAmp;

    private Long damageAmp;

    private Long armourAmp;

    private float blockChanceAmp;

    private float evasionChanceAmp;

    private Long exp;

    private Long gold;

    private String name;

    @OneToOne(mappedBy = "baseBuffs")
    private ItemDefinition itemDefinition;

    @OneToOne(mappedBy = "baseBuffs")
    private Race race;

    @OneToOne(mappedBy = "baseBuffs")
    private Class clasess;
}
