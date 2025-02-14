package com.wendril.application.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class ControllerGeneric<T, ID, R extends JpaRepository<T, ID>> {
    protected R repository;

    protected void validate(T entity, Mode mode) throws Exception {
    }

    public T save(T object) throws Exception {
        validate(object, Mode.SAVE);
        return repository.save(object);
    }

    public T update(T object) throws Exception {
        validate(object, Mode.UPDATE);
        return repository.save(object);
    }

    public void delete(T object) throws Exception {
        validate(object, Mode.DELETE);
        repository.delete(object);
    }

    public List<T> list() throws Exception {
        validate(null, Mode.LIST);
        return repository.findAll();
    }

    public T load(ID id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Não existe"));
    }

    public enum Mode {
        SAVE,
        UPDATE,
        LIST,
        DELETE,
        LOAD
    }
}
