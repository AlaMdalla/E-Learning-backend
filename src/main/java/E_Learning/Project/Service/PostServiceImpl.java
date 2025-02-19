package E_Learning.Project.Service;

import E_Learning.Project.Entity.CompressionUtil;
import E_Learning.Project.Entity.Post;
import E_Learning.Project.Repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public Post updatePost(Long postId, Post updatedPost) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post non trouvé avec l'ID : " + postId));

        // Mise à jour des champs uniquement si non null
        Optional.ofNullable(updatedPost.getTitle()).ifPresent(existingPost::setTitle);
        Optional.ofNullable(updatedPost.getContent()).ifPresent(existingPost::setContent);
        Optional.ofNullable(updatedPost.getPostedBy()).ifPresent(existingPost::setPostedBy);
        Optional.ofNullable(updatedPost.getImg()).ifPresent(existingPost::setImg);

        return postRepository.save(existingPost);
    }

    private byte[] compressBytes(byte[] bytes) {
        try {
            // Your compression logic goes here (e.g., using Java's Deflater or another library)
            return CompressionUtil.compress(bytes); // Assuming CompressionUtil is the class handling compression
        } catch (IOException e) {
            throw new RuntimeException("Error compressing image", e);
        }
    }

    public void reactPost(Long postId){
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();

            post.setLikeCount(post.getLikeCount()+1);
            postRepository.save(post);
        }else {
            throw new EntityNotFoundException("Post Not Found");
        }

    }








}
