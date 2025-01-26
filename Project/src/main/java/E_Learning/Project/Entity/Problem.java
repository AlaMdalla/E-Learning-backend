package E_Learning.Project.Entity;

import E_Learning.Project.Enums.Tags;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Problem {
    @Id
    private  Integer id;
    private String title;
    private String discription;
    private List<Tags> tags;
    private String difficulty;


}

