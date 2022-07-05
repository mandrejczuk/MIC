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
@Builder
@Table(name ="monsters")
public class Monster {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stats_id", referencedColumnName = "id")
    private Stats stats;

    @ManyToOne
    @JoinColumn(name = "monsters_definiton_id", referencedColumnName = "id")
    private MonsterDefinition monsterDefinition;

    @OneToOne(mappedBy = "monster")
    private Quest quest;
}
