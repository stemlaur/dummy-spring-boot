package fr.dalkia.dummyspringboot;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SpringContext.initContext();

        CommentServiceImpl commentService = SpringContext.getBean(CommentServiceImpl.class);
        System.out.println(commentService);

        System.out.println(commentService.joinAllComments());

    }
}
