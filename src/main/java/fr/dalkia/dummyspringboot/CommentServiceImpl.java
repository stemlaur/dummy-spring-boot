package fr.dalkia.dummyspringboot;

import fr.dalkia.dummyspringboot.framework.annotation.Bean;
import fr.dalkia.dummyspringboot.framework.annotation.Transactional;

import java.util.stream.Collectors;

@Bean
public class CommentServiceImpl {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public String joinAllComments() {
        return this.commentRepository.getAll().stream().map(Comment::comment)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "commentRepository=" + commentRepository +
                '}';
    }
}
