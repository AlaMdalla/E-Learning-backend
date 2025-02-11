package E_Learning.Project.Entity;

import jakarta.persistence.*;
import lombok.Data;

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





}
