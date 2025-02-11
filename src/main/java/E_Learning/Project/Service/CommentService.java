package E_Learning.Project.Service;

import E_Learning.Project.Entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, String postedBy, String content);
    List<Comment> getCommentByPostId(Long postId);
}
