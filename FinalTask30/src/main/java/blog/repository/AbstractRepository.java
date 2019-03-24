package blog.repository;

import java.util.List;

public interface AbstractRepository<T> {
    void save(T optional);

    List<T> getAll();

    void delete(T optional);
}
