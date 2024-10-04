package fr.dalkia.dummyspringboot;

import fr.dalkia.dummyspringboot.framework.ApplicationContext;
import fr.dalkia.dummyspringboot.framework.Spring;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ApplicationContext applicationContext = Spring.init(Application.class);

        CommentService commentService = applicationContext.getBean(CommentService.class);

        System.out.println(commentService.joinAllComments());

    }
}
