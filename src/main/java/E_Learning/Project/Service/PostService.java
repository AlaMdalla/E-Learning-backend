package E_Learning.Project.Service;

import E_Learning.Project.Entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    List<Post> getAllPosts();

    Post getPostById(Long postId);
    void deletePost(Long postId);

    void reactPost(Long postId);
    Post updatePost(Long postId, Post updatedPost);


    Post getPostByIdAndUpdating(Long postId);



}
