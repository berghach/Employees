package service;

import java.util.List;
import java.util.Optional;

public interface Services<Entity> {
    Optional<Entity> get(long id);
    List<Entity> getAll();
    boolean save(Entity entity);
    boolean update(Entity entity);
    boolean delete(Entity entity);
}
