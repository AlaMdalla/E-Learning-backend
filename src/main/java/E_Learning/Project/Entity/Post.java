package E_Learning.Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class Post {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

        private String title;

        private String content;

        private String postedBy;

        private String img;


        private Date date;

        private int viewCount;

        private int likeCount;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments;



}
