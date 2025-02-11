package E_Learning.Project.Controller;

import E_Learning.Project.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comments/create")
    public ResponseEntity<?> createComment(@RequestParam Long postId,
                                           @RequestParam String postedBy,
                                           @RequestParam String content){
        try{
            return ResponseEntity.ok(commentService.createComment(postId, postedBy, content));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

}
