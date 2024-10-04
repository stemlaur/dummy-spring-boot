package fr.dalkia.dummyspringboot;

import fr.dalkia.dummyspringboot.framework.annotation.Bean;
import fr.dalkia.dummyspringboot.framework.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Bean
public class CommentRepository {
    private static final Map<Integer, Comment> COMMENTS = new HashMap<>();

    static {
        COMMENTS.put(1, new Comment(1, "Mon commentaire 1"));
        COMMENTS.put(2, new Comment(2, "Mon commentaire 2"));
        COMMENTS.put(3, new Comment(3, "Mon commentaire 3"));
    }

    @Transactional
    public Collection<Comment> getAll() {
        System.out.println("Inside CommentRepository : getAll()");
        return COMMENTS.values();
    }

    public int count() {
        System.out.println("Inside CommentRepository : count()");
        return COMMENTS.size();
    }
}
