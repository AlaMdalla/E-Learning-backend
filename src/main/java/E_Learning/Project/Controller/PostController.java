package E_Learning.Project.Controller;

import E_Learning.Project.Entity.CompressionUtil;
import E_Learning.Project.Entity.Post;
import E_Learning.Project.Repository.PostRepository;
import E_Learning.Project.Service.PostService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.core.io.Resource;


@RestController
@RequestMapping("/blog/posts")
@CrossOrigin(origins  = "http://localhost:4200")

public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

public Post img;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            System.out.println("⚠️ Aucun fichier reçu !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fichier vide !");
        }

        // Log pour vérifier que le fichier est bien reçu
        System.out.println("✅ Fichier reçu : " + file.getOriginalFilename());
        System.out.println("Taille du fichier : " + file.getSize());
        System.out.println("Type de fichier : " + file.getContentType());

        try {
            Post img = new Post(file.getOriginalFilename(), file.getContentType(),
                    compressBytes(file.getBytes())); // Vérifie la méthode compressBytes ici
this.postRepository.save(img);
            return ResponseEntity.ok("Image bien enregistrée !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'upload de l'image : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
        }
    }


    @GetMapping("/get/{img}")
    public ResponseEntity<byte[]> getImage(@PathVariable("img") String imageName) {
        List<Optional<Post>> retrievedImages = postRepository.findByName(imageName);

        if (retrievedImages.isEmpty() || retrievedImages.get(0).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Post retrievedImage = retrievedImages.get(0).get();

        byte[] decompressedImage = decompressBytes(retrievedImage.getPicByte());

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(retrievedImage.getType())) // Type MIME correct
                .body(decompressedImage);
    }


    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
    private byte[] compressBytes(byte[] bytes) {
        try {
            // Your compression logic goes here (e.g., using Java's Deflater or another library)
            return CompressionUtil.compress(bytes); // Assuming CompressionUtil is the class handling compression
        } catch (IOException e) {
            throw new RuntimeException("Error compressing image", e);
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPost(@ModelAttribute Post post, @RequestParam("imageFile") MultipartFile file) {
        try {
            // Compress the image before setting it in the post
            post.setPicByte(compressBytes(file.getBytes())); // Compress the image bytes
            post.setName(file.getOriginalFilename()); // Set the original file name
            post.setType(file.getContentType()); // Set the MIME type of the image

            // Save the post with the compressed image
            Post createdPost = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        } catch (Exception e) {
            // Handle any exceptions that may occur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,
                                        @RequestBody Post updatedPost) {
        try {
            if (updatedPost == null) {
                return ResponseEntity.badRequest().body("Les données de mise à jour sont vides.");
            }

            Post savedPost = postService.updatePost(postId, updatedPost);
            return ResponseEntity.ok(savedPost);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post non trouvé : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllposts(){
        try{
            List<Post> posts = postService.getAllPosts();
            posts.forEach(p -> p.setPicByte(decompressBytes(p.getPicByte())));

            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        try {
            Post post = postService.getPostById(postId);
            post.setPicByte(decompressBytes(post.getPicByte())); // Correction ici
            return ResponseEntity.ok(post);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

@PutMapping("/{postId}/react")
    public ResponseEntity<?> reactPost(@PathVariable Long postId){
        try {
            postService.reactPost(postId);
            return ResponseEntity.ok(new String[]{"Post liked Successfully"});
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}
