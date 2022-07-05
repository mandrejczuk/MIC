package pz1.MIC.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Data
@Table(name ="quests")
public class Quest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;

    private String name;

    private Long exp;

    private Long prize;

    private Long duration;

    private String description;

    private float questRarity;

    private LocalDateTime startDateTime;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monster_id", referencedColumnName = "id")
    private Monster monster;
}
