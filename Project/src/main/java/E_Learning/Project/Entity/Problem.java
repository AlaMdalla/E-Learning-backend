package E_Learning.Project.Entity;

import E_Learning.Project.Enums.Tags;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    public List<Submition> getSubmitions() {
        return submitions;
    }

    private String title;
    private String description;
    private List<Tags> tags;
    private String difficulty;
    private String mainClass;
    @OneToMany(mappedBy = "problem")

    private  List<Submition> submitions;

































    public String getLinkTotestcases() {
        return linkTotestcases;
    }

    public void setLinkTotestcases(String linkTotestcases) {
        this.linkTotestcases = linkTotestcases;
    }

    private   String linkTotestcases;

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    @ElementCollection

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }





}

