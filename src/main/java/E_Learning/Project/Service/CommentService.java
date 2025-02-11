package E_Learning.Project.Service;

import E_Learning.Project.Entity.Comment;

public interface CommentService {
    Comment createComment(Long postId, String postedBy, String content);
}
