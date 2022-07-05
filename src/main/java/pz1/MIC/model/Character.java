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
@Builder(toBuilder = true)
@Data
@Table(name ="characters")
public class Character {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String img;


    private int freePoints;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "race_id", referencedColumnName = "id")
    private Race race;

    @OneToMany(mappedBy ="character",  cascade = CascadeType.ALL)
    private List<Quest> quests;

    @ManyToOne
    @JoinColumn(name = "classes_id", referencedColumnName = "id")
    private Class classes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stats_id", referencedColumnName = "id")
    private Stats stats;

    @ManyToOne
    @JoinColumn(name="character_equipment_id" ,referencedColumnName = "id")
    private CharacterEquipment characterEquipment;

}
