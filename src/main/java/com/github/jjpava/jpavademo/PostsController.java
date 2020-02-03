package com.github.jjpava.jpavademo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.jjpava.jpava.specifications.TextSpecifications.withText;

@RestController
@RequestMapping("/posts")
public class PostsController {
    private final PostsRepository postsRepository;

    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping
    public List<Post> search(@RequestParam(required = false, defaultValue = "") String query,
                             @RequestParam(required = false, defaultValue = "0") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Post> posts = postsRepository.findAll(
                withText(query).inAnyColumnOf(Post.class),
                pageRequest
        );
        return posts.toList();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postsRepository.save(post);
    }
}
