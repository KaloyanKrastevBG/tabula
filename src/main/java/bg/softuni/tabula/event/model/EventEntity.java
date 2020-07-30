package bg.softuni.tabula.event.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@Table(name = "events")
@Entity
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column
    private String description;

    @NotNull
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @NotNull
    @Column
    private Instant occurrence;


}
