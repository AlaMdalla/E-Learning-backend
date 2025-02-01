package E_Learning.Project.Repository;

import E_Learning.Project.Entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem,Integer> {
}
