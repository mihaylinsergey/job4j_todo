package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "/users/registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (!userService.save(user)) {
            model.addAttribute("error", "Пользователь с login "
                    + user.getLogin() + " уже существует");
            return "users/registration";
        }
        return "redirect:/tasks/index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "users/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
        var userOptional = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Логин или пароль введены неверно");
            return "users/login";
        }
        var session = request.getSession();
        session.setAttribute("user", userOptional.get());
        return "redirect:/tasks/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }
}
