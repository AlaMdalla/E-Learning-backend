package E_Learning.Project.Service;

import E_Learning.Project.Entity.Problem;
import E_Learning.Project.Entity.Submition;

import java.util.List;

public interface SubmitionService {
    public   Problem submitProblem(Submition submition,int idProblem);
    public Submition Update (Submition submition ,Integer id);
    public List<Submition> getSubmitions();
}
