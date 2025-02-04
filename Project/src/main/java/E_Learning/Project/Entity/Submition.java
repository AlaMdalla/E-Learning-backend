package E_Learning.Project.Entity;

import E_Learning.Project.DTO.ProblemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Submition {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    private boolean passed  ;
    @JsonIgnore
    @ManyToOne
    private  Problem problem;
    public Submition(/* parameters */) {
        // Initialization
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public Submition(Builder builder) {
        this.id = builder.id;
        this.passed = builder.passed;
        this.problem = builder.problem;
    }
    public static class Builder {
        private  Integer id;
        private boolean passed  ;
        private  Problem problem;
        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder code(boolean passed) {
            this.passed = passed;
            return this;
        }
        public Builder problem(Problem problem) {
            this.problem = problem;
            return this;
        }
        public Submition build() {
            return new Submition(this);
        }

    }

}
