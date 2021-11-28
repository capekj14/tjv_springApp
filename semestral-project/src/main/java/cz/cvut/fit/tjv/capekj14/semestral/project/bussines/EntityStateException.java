package cz.cvut.fit.tjv.capekj14.semestral.project.bussines;

public class EntityStateException extends RuntimeException {
    public <E> EntityStateException(E entity) {
        super("Illegal state of entity " + entity);
    }

    public EntityStateException() {
    }
}