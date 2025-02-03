package E_Learning.Project.Service;

import E_Learning.Project.DTO.ProblemDto;
import E_Learning.Project.Entity.Problem;
import E_Learning.Project.Mapper.ProblemMapper;
import E_Learning.Project.Mapper.ProblemMapperImpl;
import E_Learning.Project.Repository.ProblemRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImp implements ProblemService {
    private ProblemRepository problemRepository;
    private ProblemMapper problemMapper =new ProblemMapperImpl();

    public ProblemServiceImp( ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }
    public ProblemDto addProblem(Problem problem){
        return problemMapper.toDto(this.problemRepository.save(problem)) ;
    }

    @Override
    public List<Problem> getProblems() {
        return this.problemRepository.findAll();
    }
    public Problem getProblem( Integer id) {return  this.problemRepository.findById(id).get();}

}
