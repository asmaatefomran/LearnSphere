package com.example.LMS.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.LMS.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LMS.model.User;

@Repository
public class UserRepo {
    private final Map<Long, User> users = new HashMap<>();


    public User saveUser(User user) {
        user.setId();

        users.put(user.getId(), user);
        System.out.println("user saved"+ user.getName());
        return user;
    }

    private void updatelambda(Runnable setter, Object newValue) {
        if (newValue != null) {
            setter.run();
        }
    }

    public User updateUser(User u) {
        User userToUpdate = getUserById(u.getId());
        updatelambda(() -> userToUpdate.setEmail(u.getEmail()), u.getEmail());
        updatelambda(() -> userToUpdate.setName(u.getName()), u.getName());
        updatelambda(() -> userToUpdate.setPassword(u.getPassword()), u.getPassword());
        return userToUpdate;
    }


    public Optional<User> findUserViaEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
    
    public boolean existsByEmail(String email) {
        return findUserViaEmail(email).isPresent();
    }

    public Optional<User> findById(Long id) {
          return Optional.ofNullable(users.get(id));
    }

    public List<User> findAllUsers() {

        return new ArrayList<>(users.values());
    }
    public void deleteById(Long id) {
        users.remove(id);
    }
    public User getUserById(Long id) {
        if (!users.containsKey(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
        return users.get(id);
    }

}
