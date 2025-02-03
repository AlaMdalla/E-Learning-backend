package E_Learning.Project.Controller;

import E_Learning.Project.CodeRunner;
import E_Learning.Project.Entity.Problem;
import E_Learning.Project.Entity.Submition;
import E_Learning.Project.Service.ProblemService;
import E_Learning.Project.Service.SubmitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submition")
public class SubmitionController {
    private SubmitionService submitionService;

    public SubmitionController(SubmitionService submitionService, ProblemService problemService) {
        this.submitionService = submitionService;
        this.problemService = problemService;
    }

    private ProblemService problemService;


    @PostMapping("/submit/{problemId}")
    public ResponseEntity<?> submitProblem(@RequestBody String code, @PathVariable Integer problemId) {
        Submition submition =new Submition();
        Problem problem = this.submitionService.submitProblem(submition,problemId);
        String mainClass = problem.getMainClass();
        CodeRunner codeRunner =new CodeRunner(mainClass,code);
        try {
            List<Submition> submissions = problem.getSubmitions();
            if (submissions != null && !submissions.isEmpty()) {
                Submition lastSubmition = submissions.get(submissions.size() - 1);
                Integer lastSubmitionId = lastSubmition.getId();
                submition.setId(lastSubmitionId);
            }

if(codeRunner.runTestCases().equals("✅ All test cases passed!")){
    System.out.println("eeeeeeeeeeeeeeeeeeeeeee"+submition.getId());
    submition.setPassed(true);
    submition =  this.submitionService.Update(submition,152);
}
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type", "text/plain")
                    .body(codeRunner.runTestCases());

        }catch (Exception exception)
        {
            System.out.println("pppp");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "text/plain")
                .body("noooo");
    }
    @GetMapping("/")
    List<Submition> getSubmitions(){
        return this.submitionService.getSubmitions();    }


    }
