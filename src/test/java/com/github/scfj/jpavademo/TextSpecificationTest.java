package com.github.scfj.jpavademo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.scfj.jpava.specifications.TextSpecifications.withText;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TextSpecificationTest {
    private final PostsRepository postsRepository;

    @Autowired
    public TextSpecificationTest(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    Post wordsEverywhere = new Post("has 'word' in title", "has 'word' in text");
    Post wordInTitle = new Post("has 'word' in title", "has no 'w0rd' in text");
    Post wordInText = new Post("has no 'w0rd' in title", "has no 'word' in text");
    Post noWords = new Post("has no 'w0rd' in title", "has no 'w0rd' in text");

    List<Post> searchResult;

    @BeforeEach
    public void fillDatabase() {
        postsRepository.saveAll(asList(wordsEverywhere, wordInTitle, wordInText, noWords));
        searchResult = postsRepository.findAll(withText("Word").inAnyColumnOf(Post.class));
    }

    @Test
    public void shouldReturnPostWithSubstringInPreview() {
        assertTrue(searchResult.contains(wordInTitle));
    }

    @Test
    public void shouldReturnPostWithSubstringInEveryColumn() {
        assertTrue(searchResult.contains(wordsEverywhere));
    }

    @Test
    public void shouldReturnPostWithSubstringInText() {
        assertTrue(searchResult.contains(wordInText));
    }

    @Test
    public void shouldNotReturnPostWithNoSubstringAtAll() {
        assertFalse(searchResult.contains(noWords));
    }
}
