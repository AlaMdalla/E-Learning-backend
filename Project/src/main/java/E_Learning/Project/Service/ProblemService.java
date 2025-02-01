package E_Learning.Project.Service;

import E_Learning.Project.DTO.ProblemDto;
import E_Learning.Project.Entity.Problem;

import java.util.List;

public interface ProblemService {
    public ProblemDto addProblem(Problem problem);
    public List<Problem> getProblems();
}
