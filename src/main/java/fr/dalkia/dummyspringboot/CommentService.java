package fr.dalkia.dummyspringboot;

import fr.dalkia.dummyspringboot.framework.annotation.Bean;

import java.util.stream.Collectors;

@Bean
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public String joinAllComments() {
        System.out.println("Joining all comments");
        int numberOfComment = this.commentRepository.count();
        return this.commentRepository.getAll().stream().map(Comment::comment)
                .collect(Collectors.joining(", ")) + " nombre: " + numberOfComment;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "commentRepository=" + commentRepository +
                '}';
    }
}
