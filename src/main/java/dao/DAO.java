package dao;


import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Optional;

public interface DAO<Entity> {
    Optional<Entity> get(long id);
    List<Entity> getAll();
    void save(Entity entity) throws  SystemException;

    void update(Entity entity) throws SystemException;
    boolean delete(Entity entity) throws SystemException;
}