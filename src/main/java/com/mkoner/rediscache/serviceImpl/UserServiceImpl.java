package com.mkoner.rediscache.serviceImpl;

import com.mkoner.rediscache.entity.User;
import com.mkoner.rediscache.repository.UserRepository;
import com.mkoner.rediscache.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @CachePut(value = "users", key = "#id")
    public User updateUser(User user, Long id) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No user found with the id " + id));
        if(!user.getFirstName().isBlank() && Objects.nonNull((user.getFirstName()))) userToUpdate.setFirstName(user.getFirstName());
        if(!user.getLastName().isBlank() && Objects.nonNull(user.getLastName())) userToUpdate.setLastName(user.getLastName());
        return userRepository.save(userToUpdate);
    }

    @Override
    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        System.out.println("DB called");
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        System.out.println("DB called");
        return userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No user found with the id " + id));
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
