package E_Learning.Project.Service;

import E_Learning.Project.CodeRunner;
import E_Learning.Project.Entity.Problem;
import E_Learning.Project.Entity.Submition;
import E_Learning.Project.Repository.ProblemRepository;
import E_Learning.Project.Repository.SubmitionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmitionServiceImp implements SubmitionService {
    private SubmitionRepository submitionRepository;
    private ProblemRepository problemRepository;

    public SubmitionServiceImp(SubmitionRepository submitionRepository, ProblemRepository problemRepository) {
        this.submitionRepository = submitionRepository;
        this.problemRepository = problemRepository;
    }

    @Override
    public Problem submitProblem(Submition submition ,int idProblem) {
        Problem problem= problemRepository.findById(idProblem).get();
        List<Submition> submitions=problem.getSubmitions();
         String mainClass =problem.getMainClass();
        System.out.println(mainClass);
       if(problem!= null){
            submition.setProblem(problem);
           submitions.add(submition);
           problemRepository.save(problem);
            submitionRepository.save(submition);
            System.out.println("entedddd");
       return problem;}
       return problem;


    }

public  Submition Update (Submition submition ,Integer id){
        Submition submition1 = this.submitionRepository.findById(id).get();
        submition1.setPassed(submition.isPassed());
        return  this.submitionRepository.save(submition1);
}
    public List<Submition> getSubmitions() {
        return this.submitionRepository.findAll();
    }

}
