package blog.repository;

import java.util.List;

public interface AbstractRepository<T> {
    void create(T optional);

    List<T> getAll();

    void delete(T optional);

    T getById(Integer id);
}
