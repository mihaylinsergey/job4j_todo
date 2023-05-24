package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TaskService;

@ThreadSafe
@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/index")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "/tasks/index";
    }

    @GetMapping("/done")
    public String getDoneTasks(Model model) {
        model.addAttribute("doneTasks", taskService.findDoneOrNew(true));
        return "/tasks/done";
    }

    @GetMapping("/new")
    public String getNewTasks(Model model) {
        model.addAttribute("newTasks", taskService.findDoneOrNew(false));
        return "/tasks/new";
    }

    @GetMapping("/create")
    public String getCreateView() {
        return "/tasks/create";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task, @SessionAttribute User user, Model model) {
        task.setUser(user);
        if (!taskService.save(task)) {
            model.addAttribute("message", "Задача не сохранена, попробуйте еще раз!");
            return "errors/404";
        }
        return "redirect:/tasks/index";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskServiceOptional = taskService.findById(id);
        if (taskServiceOptional.isEmpty()) {
            model.addAttribute("message", "Задача не найдена, попробуйте еще раз!");
            return "errors/404";
        }
        model.addAttribute("task", taskServiceOptional.get());
        return "/tasks/task";
    }

    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable int id, Model model) {
        if (!taskService.complete(id)) {
            model.addAttribute("message", "Задача не отмечена исполненной, попробуйте еще раз!");
            return "errors/404";
        }
        return "redirect:/tasks/index";
    }

    @PostMapping("/updateView")
    public String updateView(@ModelAttribute Task task, Model model) {
        var taskServiceOptional = taskService.findById(task.getId());
        if (taskServiceOptional.isEmpty()) {
            model.addAttribute("message", "Задача не найдена, попробуйте еще раз!");
            return "errors/404";
        }
        model.addAttribute("task", taskServiceOptional.get());
        return "/tasks/update";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task, Model model) {
        if (!taskService.update(task)) {
            model.addAttribute("message", "Задача не обновлена, попробуйте еще раз!");
            return "errors/404";
        }
        return "redirect:/tasks/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id, Model model) {
        if (!taskService.delete(id)) {
            model.addAttribute("message", "Задача не удалена, попробуйте еще раз!");
            return "errors/404";
        }
        return "redirect:/tasks/index";
    }
}
