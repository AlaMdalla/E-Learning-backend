package E_Learning.Project.Controller;

import E_Learning.Project.CodeRunner;
import E_Learning.Project.DTO.ProblemDto;
import E_Learning.Project.Entity.Problem;
import E_Learning.Project.Enums.Tags;
import E_Learning.Project.Service.*;
import jakarta.persistence.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/problem")
public class ProblemController {

private ProblemService problemService;
    FileReaderService fileReaderService = new FileReaderServiceImpl();
    CodeCompiler codeCompiler = new CodeCompilerJavaImpl();
    TestCaseExecutor testCaseExecutor = new TestCaseExecutorJavaImpl();

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
    @PostMapping("/submit")
    ResponseEntity submitProblem(@RequestBody String code){
  String mainClass =this.problemService.getProblem(352).getMainClass();
        CodeRunner codeRunner = new CodeRunner(fileReaderService, codeCompiler, testCaseExecutor);
        codeRunner.run(mainClass,code);

      //  CodeRunner codeRunner =new CodeRunner(mainClass,code);
       try {

           return ResponseEntity.status(HttpStatus.OK)
                   .header("Content-Type", "text/plain")
                   .body(codeRunner.result);

       }catch (Exception exception)
       {
           System.out.println("pppp");
       }
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "text/plain")
                .body("noooo");
    }
}
