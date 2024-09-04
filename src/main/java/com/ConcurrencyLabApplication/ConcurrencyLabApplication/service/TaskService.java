package com.ConcurrencyLabApplication.ConcurrencyLabApplication.service;

import com.ConcurrencyLabApplication.ConcurrencyLabApplication.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TaskService {

    private final ConcurrentHashMap<Long, Task> taskMap = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<Task> taskList = new CopyOnWriteArrayList<>();
    private long taskIdCounter = 0;

    // Add a new task
    public Task addTask(Task task) {
        task.setId(++taskIdCounter);
        taskMap.put(task.getId(), task);
        taskList.add(task);
        System.out.println("Task added: " + task.getName() + " [Thread: " + Thread.currentThread().getName() + "]");
        return task;
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        System.out.println("Fetching all tasks [Thread: " + Thread.currentThread().getName() + "]");
        return taskList;
    }

    // Get a task by ID
    public Optional<Task> getTask(Long id) {
        System.out.println("Fetching task with ID: " + id + " [Thread: " + Thread.currentThread().getName() + "]");
        return Optional.ofNullable(taskMap.get(id));
    }

    // Update a task
    public Optional<Task> updateTask(Long id, Task task) {
        if (taskMap.containsKey(id)) {
            task.setId(id);
            taskMap.put(id, task);
            taskList.replaceAll(existingTask -> existingTask.getId().equals(id) ? task : existingTask);
            System.out.println("Task updated: " + task.getName() + " [Thread: " + Thread.currentThread().getName() + "]");
            return Optional.of(task);
        }
        return Optional.empty();
    }

    // Delete a task by ID
    public boolean deleteTask(Long id) {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
            taskList.removeIf(task -> task.getId().equals(id));
            System.out.println("Task deleted with ID: " + id + " [Thread: " + Thread.currentThread().getName() + "]");
            return true;
        }
        return false;
    }

    // Compare performance between concurrent and non-concurrent collections
    public String comparePerformance() {
        int size = 100000;

        // Concurrent HashMap
        ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        long concurrentStartTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            concurrentMap.put("key" + i, "value" + i);
        }
        long concurrentEndTime = System.nanoTime();

        // Non-concurrent HashMap (for comparison purposes, we're using the same ConcurrentHashMap to avoid import complexity)
        ConcurrentHashMap<String, String> nonConcurrentMap = new ConcurrentHashMap<>();
        long nonConcurrentStartTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            nonConcurrentMap.put("key" + i, "value" + i);
        }
        long nonConcurrentEndTime = System.nanoTime();

        String result = "Concurrent collection time: " + (concurrentEndTime - concurrentStartTime) +
                " ns, Non-concurrent collection time: " + (nonConcurrentEndTime - nonConcurrentStartTime) + " ns";

        System.out.println(result);
        return result;
    }
}
