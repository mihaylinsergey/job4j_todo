package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@ThreadSafe
@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/index")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "/index";
    }

    @GetMapping("/done")
    public String getDoneTasks(Model model) {
        model.addAttribute("doneTasks", taskService.findDone());
        return "/done";
    }

    @GetMapping("/new")
    public String getNewTasks(Model model) {
        model.addAttribute("newTasks", taskService.findNew());
        return "/new";
    }

    @GetMapping("/create")
    public String getCreateView() {
        return "/create";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/index";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        model.addAttribute("task", taskService.findById(id).get());
        return "/task";
    }

    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable int id) {
        taskService.complete(id);
        return "redirect:/index";
    }

    @PostMapping("/updateView")
    public String updateView(@ModelAttribute Task task, Model model) {
        model.addAttribute("task", taskService.findById(task.getId()).get());
        return "update";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/index";
    }
}
