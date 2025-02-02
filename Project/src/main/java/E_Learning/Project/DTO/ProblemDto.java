package E_Learning.Project.DTO;

import E_Learning.Project.Entity.TestCase;
import E_Learning.Project.Enums.Tags;

import java.util.List;

public class ProblemDto {
    private Integer id;
    private String title;
    private String description;
    private List<Tags> tags;
    private String difficulty;
    private   String linkTotestcases;
    private String mainClass;

    private ProblemDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.tags = builder.tags;
        this.difficulty = builder.difficulty;
        this.linkTotestcases=builder.linkTotestcases;
        this.mainClass=builder.mainClass;

    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public String getDifficulty() {
        return difficulty;
    }



    public static class Builder {
        private Integer id;
        private String title;
        private String description;
        private List<Tags> tags;
        private String difficulty;
        private   String linkTotestcases;
        private String mainClass;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder linkTotestcases(String linkTotestcases) {
            this.linkTotestcases = linkTotestcases;
            return this;
        }
        public Builder mainClass(String mainClass) {
            this.mainClass = mainClass;
            return this;
        }


        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder tags(List<Tags> tags) {
            this.tags = tags;
            return this;
        }

        public Builder difficulty(String difficulty) {
            this.difficulty = difficulty;
            return this;
        }



        public ProblemDto build() {
            return new ProblemDto(this);
        }
    }
}
