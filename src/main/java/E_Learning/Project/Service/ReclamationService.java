package E_Learning.Project.Service;

import E_Learning.Project.Entity.Reclamation;

import java.util.List;

public interface ReclamationService {
    Reclamation createReclamation(Long postId, String reason, String email, String name);
    List<Reclamation> getReclamationByPostId(Long postId);
}
