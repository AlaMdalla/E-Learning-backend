package E_Learning.Project.Service;

import E_Learning.Project.Entity.Post;
import E_Learning.Project.Repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;


    public Post savePost(Post post){
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setDate(new Date());
        return postRepository.save(post);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    public Post getPostById(Long postId){
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();

            post.setViewCount(post.getViewCount() + 1);
            return postRepository.save(post);
        }else {
            throw new EntityNotFoundException("Post not Found");
        }
    }
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with ID " + postId + " not found");
        }
        postRepository.deleteById(postId);
    }



}
