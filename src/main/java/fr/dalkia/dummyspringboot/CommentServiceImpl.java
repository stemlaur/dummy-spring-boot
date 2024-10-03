package fr.dalkia.dummyspringboot;

import java.util.stream.Collectors;

public class CommentServiceImpl {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

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
