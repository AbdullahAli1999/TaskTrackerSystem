package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.Api.ApiResponse;
import com.example.tasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    ArrayList<Task> tasks = new ArrayList<>();

    //GET
    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    //ADD
    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse("Task added successfully");
    }
    //UPDATE
    @PutMapping("update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return new ApiResponse("Task updated successfully");
    }

    //DELETE
    @DeleteMapping("delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("Task deleted successfully");
    }

    //CHANGE
    @PutMapping("change/{index}/{status}")
    public ApiResponse changeStatus(@PathVariable int index, String status) {
        tasks.get(index).setStatus(status);
        return new ApiResponse("Task status changed successfully");
    }


    //SEARCH
    @GetMapping("/search/{title}")//
    public ApiResponse searchTasks(@PathVariable String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return new ApiResponse("Task found: " + task.getTitle());
            }
        }
        return new ApiResponse("Task not foundd");
    }

}
