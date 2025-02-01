package E_Learning.Project.Mapper;

import E_Learning.Project.DTO.ProblemDto;
import E_Learning.Project.Entity.Problem;

public interface ProblemMapper {
    ProblemDto toDto(Problem problem);

    Problem toEntity(ProblemDto problemDto);

}
