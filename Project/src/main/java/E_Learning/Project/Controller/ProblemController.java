package E_Learning.Project.Controller;

import E_Learning.Project.DTO.ProblemDto;
import E_Learning.Project.Entity.Problem;
import E_Learning.Project.Enums.Tags;
import E_Learning.Project.Service.ProblemService;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/problem")
public class ProblemController {

private ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }
    @PostMapping("/")
    ProblemDto addProblem(@RequestBody Problem p){
        return this.problemService.addProblem(p);
    }
    @GetMapping("/")
    List<Problem> getProblems(){
        return this.problemService.getProblems();    }
}
