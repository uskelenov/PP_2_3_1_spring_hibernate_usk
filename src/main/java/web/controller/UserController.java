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

//        userService.add(new User("User1", "lastName1", (byte) 21, "prof1"));
//        userService.add(new User("User2", "lastName2", (byte) 22, "prof2"));
//        userService.add(new User("User3", "lastName3", (byte) 23, "prof3"));
//        userService.add(new User("User4", "lastName4", (byte) 24, "prof4"));
//        userService.add(new User("User5", "lastName5", (byte) 25, "prof5"));


//        userService.changeUser(new User(5L,"User5", "lastName5", (byte) 25, "prof5"));

//        userService.deleteUser(6L);

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

//    @RequestMapping(value = "users", method = RequestMethod.POST)
//    public String creatNewUser(@ModelAttribute("creatUser") User creatUser,
//                        BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("users", userService.listUser());
//            return "users";
//        }
//        userService.add(creatUser);
//        return "redirect:users";
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:users";
//    }

//    @RequestMapping(value = "users", method = RequestMethod.POST)
//    public String deleteUser(@ModelAttribute("deleteUser") User deleteUser,
//                             BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("users", userService.listUser());
//            return "users";
//        }
//        userService.deleteUser();
//        return "redirect:users";
//    }
}
