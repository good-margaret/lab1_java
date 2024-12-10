package tech.reliab.course.tishchenko.bank.service;

import java.util.List;
import java.util.Optional;

public interface BaseOperationsService<T> {
//    void update(int id, T entity);
    void delete(int id);
    Optional<T> getById(int id);
    T getByIdIfPossible(int id);
    List<T> getAll();
}
