package E_Learning.Project.Service;

import E_Learning.Project.Entity.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    List<Post> getAllPosts();

    Post getPostById(Long postId);
    void deletePost(Long postId);
}
