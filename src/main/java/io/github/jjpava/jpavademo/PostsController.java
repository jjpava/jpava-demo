package io.github.jjpava.jpavademo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.jjpava.jpava.specifications.TextSpecifications.withText;

@RestController
@RequestMapping("/posts")
public class PostsController {
    private final PostsRepository postsRepository;

    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping
    public List<Post> search(@RequestParam(defaultValue = "") String query,
                             @RequestParam Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Post> posts = postsRepository.findAll(
                withText(query).inAnyColumnOf(Post.class),
                pageRequest
        );
        return posts.toList();
    }

    @GetMapping
    public List<Post> search(@RequestParam(defaultValue = "") String query) {
        return postsRepository.findAll(withText(query).inAnyColumnOf(Post.class));
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postsRepository.save(post);
    }
}
