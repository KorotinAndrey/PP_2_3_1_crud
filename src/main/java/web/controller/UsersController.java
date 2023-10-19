package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/all";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/showUser";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";

        userService.addUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") long id ){
        model.addAttribute("user",userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute ("user") @Valid  User updateUser,
                          BindingResult bindingResult, @PathVariable ("id") long id) {
        if (bindingResult.hasErrors())
            return "users/edit";

        userService.updateUser(id, updateUser);
        return "redirect:/users/all";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users/all";
    }

}