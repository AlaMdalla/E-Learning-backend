package E_Learning.Project.Service;

import E_Learning.Project.Entity.Comment;
import E_Learning.Project.Entity.Post;
import E_Learning.Project.Entity.Reclamation;
import E_Learning.Project.Repository.PostRepository;
import E_Learning.Project.Repository.ReclamationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReclamationServiceImpl implements ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private PostRepository postRepository;



    public Reclamation createReclamation(Long postId, String reason, String email, String name) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Reclamation reclamation = new Reclamation();
        reclamation.setPost(post);
        reclamation.setReason(reason);
        reclamation.setEmail(email);
        reclamation.setName(name);

        return reclamationRepository.save(reclamation);
    }


    public List<Reclamation> getReclamationByPostId(Long postId) {
        return reclamationRepository.findByPostId(postId);
    }
}
