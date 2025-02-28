package E_Learning.Project.Service;

import E_Learning.Project.Entity.Reclamation;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ReclamationService {
    Reclamation createReclamation(Long postId, String reason, String email, String name);
    List<Reclamation> getAllReclamations();
    Reclamation getReclamationById(Long id);
    Reclamation updateReclamation(Long id, Reclamation reclamationDetails);
    void deleteReclamation(Long id);
    List<Reclamation> getReclamationByPostId(Long postId);

}
