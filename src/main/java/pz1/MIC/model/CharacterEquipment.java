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
@Builder
@Data
@Table(name = "characters_equipment")
public class CharacterEquipment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "characterEquipment",cascade = CascadeType.PERSIST)
    private List<Character> characters;

    @OneToMany(mappedBy = "characterEquipment",cascade = CascadeType.PERSIST)
    private List<Item> items;
}
