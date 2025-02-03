package E_Learning.Project.Mapper;

import E_Learning.Project.DTO.ProblemDto;
import E_Learning.Project.Entity.Problem;

public class ProblemMapperImpl implements ProblemMapper{
    @Override
    public ProblemDto toDto(Problem problem) {


        ProblemDto problemDto = new ProblemDto.Builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .description(problem.getDescription())
                .tags(problem.getTags())
                .submitions(problem.getSubmitions())
                .difficulty(problem.getDifficulty())
                .linkTotestcases(problem.getLinkTotestcases())
                .mainClass(problem.getMainClass())


                .build();

        return problemDto;
  }

    @Override
    public Problem toEntity(ProblemDto problemDto) {

         Problem problem=   new Problem();
              /*   problem.builder().
                 id(problemDto.getId()).title(problemDto.getTitle())
                 .description(problemDto.getDescription())
                 .tags(problemDto.getTags())
                 .difficulty(problemDto.getDifficulty())
                 .inputFormat(problemDto.getInputFormat())
                 .outputFormat(problemDto.getOutputFormat())


                 .build();*/
         return problem;
    }
}
