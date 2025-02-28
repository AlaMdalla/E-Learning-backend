package E_Learning.Project.Controller;


import E_Learning.Project.Entity.Reclamation;
import E_Learning.Project.Repository.ReclamationRepository;
import E_Learning.Project.Service.CommentService;
import E_Learning.Project.Service.ReclamationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/posts")
@CrossOrigin(origins  = "http://localhost:4200")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping("reclamations/create")
    public ResponseEntity<?> createReclamation(@RequestParam Long postId,
                                               @RequestParam String reason,
                                               @RequestParam String email,
                                               @RequestParam String name) {
        try {
            return ResponseEntity.ok(reclamationService.createReclamation(postId, reason, email, name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("reclamations/{postId}")
    public ResponseEntity<?> getReclamationsByPostId(@PathVariable Long postId) {
        try {
            return ResponseEntity.ok(reclamationService.getReclamationByPostId(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }




}
