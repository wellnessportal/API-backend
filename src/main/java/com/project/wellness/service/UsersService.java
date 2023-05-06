package com.project.wellness.service;

import com.project.wellness.model.Users;
import com.project.wellness.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void addNewUser(Users users) {
        usersRepository.save(users);
    }
    public List<Users> findAll() {
        return   usersRepository.findAll();
    }

    public String findUserStat(String id){
        Users user1=usersRepository.findById(id).orElse(null);
        if(user1==null)
            return "Invalid User";

        return user1.getStatus(); }

    public Optional<Users> updateUser(String id, Users updatedUsers){
        Users user=usersRepository.findById(id).orElse(null);
        if(user==null){
            return Optional.empty();
        }
        user.setEmail_id(updatedUsers.getEmail_id());
        user.setStatus(updatedUsers.getStatus());
        usersRepository.save(user);
        return Optional.of(user);
    }
    public void deleteUser(String id) {
        boolean exists=usersRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("users with id "+id+" does not exists");
        }
        usersRepository.deleteById(id);
    }

    public String increaseUserRating(String id) {
        Users user=usersRepository.findById(id).orElse(null);
        user.setRating(user.getRating()+1);
        usersRepository.save(user);
        return "User rating increased by 1%";
    }

    public String decreaseUserRating(String id) {
        Users user=usersRepository.findById(id).orElse(null);
        user.setRating(user.getRating()-1);
        usersRepository.save(user);
        return "User rating decreased by 1%";
    }

    public int getUserRating(String id) {
        Users user=usersRepository.findById(id).orElse(null);
        return user.getRating();
    }
}

