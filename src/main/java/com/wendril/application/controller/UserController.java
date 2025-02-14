package com.wendril.application.controller;

import com.wendril.application.model.User;
import com.wendril.application.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserController extends ControllerGeneric<User, Long, UserRepository> {

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public List<User> list() {
        return this.repository.findAll();
    }

    public User load(String username) throws Exception {

        return this.repository.findByUsername(username)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

    }

    @Transactional
    public void updateUser(User userAtualizado) throws Exception {
        Optional<User> userOptional = repository.findById(userAtualizado.getId());
        if (userOptional.isPresent()) {
            repository.save(userAtualizado);
        } else {
            throw new Exception("Usuário não encontrado");
        }
    }

    @Override
    protected void validate(User entity, Mode mode) throws Exception {
        super.validate(entity, mode);

        switch (mode) {
            case SAVE:
                System.out.println("save");
                if (repository.existsByUsername(entity.getUsername())) throw new Exception("Usuário já cadastrado");
                break;
            case UPDATE:
                System.out.println("update");
                if (!repository.existsById(entity.getId())) throw new Exception("Não existe");
                break;
            case DELETE:
                System.out.println("delete");
                if (!repository.existsById(entity.getId())) throw new Exception("Não existe");
                break;
        }
    }

}
