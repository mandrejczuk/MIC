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
@Table(name ="monsters_def")
public class MonsterDefinition {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name = "id")

        private Long id;

        private String name;

        private Long rarity;

        private String imgRef;

        @OneToOne
        @JoinColumn(name = "base_stats_id", referencedColumnName = "id")
        private BaseStats baseStats;

        @OneToMany(mappedBy = "monsterDefinition")
        private List<Monster> monsters;
}
