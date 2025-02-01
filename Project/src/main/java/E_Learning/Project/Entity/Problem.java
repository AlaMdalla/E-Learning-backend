package E_Learning.Project.Entity;

import E_Learning.Project.Enums.Tags;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    private String title;
    private String description;
    private List<Tags> tags;
    private String difficulty;
    /*private   String inputFormat;
    private  String outputFormat;*/
    private List<String> examples;

    public String getLinkToProgram() {
        return linkToProgram;
    }

    public void setLinkToProgram(String linkToProgram) {
        this.linkToProgram = linkToProgram;
    }

    public String getLinkTotestcases() {
        return linkTotestcases;
    }

    public void setLinkTotestcases(String linkTotestcases) {
        this.linkTotestcases = linkTotestcases;
    }

    private   String linkTotestcases;
    private  String linkToProgram;

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    @ElementCollection
    //private  List<TestCase> testCases;

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

  /*  public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }*/

  /*  public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }*/
}

