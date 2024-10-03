package fr.dalkia.dummyspringboot;

import fr.dalkia.dummyspringboot.framework.config.ConfigParser;

import java.io.IOException;

public class Application {

    // 3 problems :
    // instances differentes de CommentRepository
    // reconstruction des beans à chaque appel à getBean
    // tout est considéré comme un bean spring ?
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SpringContext.initContext();

        CommentServiceImpl commentService = SpringContext.getBean(CommentServiceImpl.class);
        System.out.println(commentService);

        System.out.println(commentService.joinAllComments());

//        Product product = SpringContext.getBean(Product.class);
//        System.out.println(product);

    }
}
