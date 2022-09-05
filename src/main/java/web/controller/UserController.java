package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {

        model.addAttribute("users", userService.listUser());

        model.addAttribute("creatUser", new User());

        model.addAttribute("deleteUser", new User());

        return "users";
    }

    @GetMapping("{id}")
    public String showUser(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "user-page";
    }

    @GetMapping("new")
    public String newUser(Model model) {
        model.addAttribute("creatUser", new User());
        return "new";
    }

    @PostMapping()
    public String creatNewUser(@ModelAttribute("creatUser") User creatUser,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.listUser());
            return "new";
        }
        userService.add(creatUser);
        return "redirect:users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("editUser", userService.showUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("editUser") User editUser) {
        userService.changeUser(editUser);
        return "redirect:/users";
    }
}
