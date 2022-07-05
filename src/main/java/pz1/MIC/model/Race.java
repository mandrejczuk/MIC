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
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;

    private String name;

    @OneToMany(mappedBy = "race")
    private List<Character> characters;

    @OneToOne
    @JoinColumn(name = "base_buffs_id", referencedColumnName = "id")
    private BaseBuffs baseBuffs;

    @OneToOne
    @JoinColumn(name = "base_stats_id", referencedColumnName = "id")
    private BaseStats baseStats;
}
