package fr.dalkia.dummyspringboot;

public class Application {

    // 3 problems :
    // instances differentes de CommentRepository
    // reconstruction des beans à chaque appel à getBean
    // tout est considéré comme un bean spring ?
    public static void main(String[] args) {
        CommentRepository commentRepository = SpringContext.getBean(CommentRepository.class);
        System.out.println(commentRepository);
        CommentServiceImpl commentService = SpringContext.getBean(CommentServiceImpl.class);
        System.out.println(commentService);

        System.out.println(commentService.joinAllComments());
    }
}
