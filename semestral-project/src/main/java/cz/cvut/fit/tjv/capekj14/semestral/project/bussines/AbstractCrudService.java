package cz.cvut.fit.tjv.capekj14.semestral.project.bussines;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractCrudService<K, E> {

    protected final JpaRepository<E, K> repository;

    public AbstractCrudService(JpaRepository<E, K> repository) {
        this.repository = repository;
    }

    public void create(E entity){
        if (exists(entity))
            throw new EntityStateException(entity);
        repository.save(entity);
    }

    public abstract boolean exists(E entity);

    public Optional<E> findById(K id) {

        return repository.findById(id);
    }

    public Collection<E> findAll() {

        return repository.findAll();
    }

    public void update(E entity) {
        if (exists(entity))
            repository.save(entity);
        else
            throw new EntityStateException(entity);
    }
    public void deleteById(K id) {
        repository.deleteById(id);
    }
}