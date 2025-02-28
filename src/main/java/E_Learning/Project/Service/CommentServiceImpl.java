package E_Learning.Project.Service;

import E_Learning.Project.Entity.Comment;
import E_Learning.Project.Entity.Post;
import E_Learning.Project.Repository.CommentRepository;
import E_Learning.Project.Repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment createComment(Long postId, String postedBy, String content){
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()){
            Comment comment = new Comment();
            comment.setPost(optionalPost.get());
            comment.setContent(content);
            comment.setPostedBy(postedBy);
            comment.setCreatedAt(new Date());

            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Post Not Found");
    }
    public Comment replyToComment(Long parentCommentId, String postedBy, String content) {
        Optional<Comment> optionalParentComment = commentRepository.findById(parentCommentId);
        if (optionalParentComment.isPresent()) {
            Comment parentComment = optionalParentComment.get();
            Comment reply = new Comment();
            reply.setParentComment(parentComment); // Link to the parent comment
            reply.setPost(parentComment.getPost()); // Ensure the reply is tied to the same post
            reply.setContent(content);
            reply.setPostedBy(postedBy);
            reply.setCreatedAt(new Date());

            // Save the reply and add it to the parent's replies list
            Comment savedReply = commentRepository.save(reply);
            parentComment.getReplies().add(savedReply);
            commentRepository.save(parentComment); // Update the parent with the new reply

            return savedReply;
        }
        throw new EntityNotFoundException("Parent Comment Not Found");
    }

    public List<Comment> getCommentByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }
}
