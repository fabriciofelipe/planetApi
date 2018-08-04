package planet.data.planetapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "planet")
public class Planet {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String climate;
    @NotNull
    private String terrain;
    private Long filmsOccurs = 0L;
}
