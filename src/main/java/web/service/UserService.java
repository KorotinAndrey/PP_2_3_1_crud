package web.service;

import web.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    List <User> getAllUsers();
    User getUserById (long id);
    void addUser(User user);
    void delete(long id);
    void updateUser (long id, User updateUser);

}