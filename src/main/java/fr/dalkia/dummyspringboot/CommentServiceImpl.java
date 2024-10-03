package fr.dalkia.dummyspringboot;

public class CommentServiceImpl {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public String toString() {
        return "CommentServiceImpl{" +
                "commentRepository=" + commentRepository +
                '}';
    }
}
