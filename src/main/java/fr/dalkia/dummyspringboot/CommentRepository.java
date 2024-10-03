package fr.dalkia.dummyspringboot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommentRepository {
    private static final Map<Integer, Comment> COMMENTS = new HashMap<>();

    static {
        COMMENTS.put(1, new Comment(1, "Mon commentaire 1"));
        COMMENTS.put(2, new Comment(2, "Mon commentaire 2"));
        COMMENTS.put(3, new Comment(3, "Mon commentaire 3"));
    }

    public Collection<Comment> getAll() {
        return COMMENTS.values();
    }
}
