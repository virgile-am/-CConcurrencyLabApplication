
# Concurrency and Multithreading Lab

## Project Overview
This project demonstrates the concepts of concurrency and multithreading in Java using a Spring Boot application. It includes practical implementations of concurrent collections such as `ConcurrentHashMap` and `CopyOnWriteArrayList`, along with a performance comparison between concurrent and non-concurrent collections.

## Lab Objectives
- Understand the concepts of concurrency and multithreading.
- Explore Java's concurrency utilities.
- Utilize concurrent collections effectively.

## Prerequisites
- Java 21 or higher
- Spring Boot 3.x.x
- Maven for dependency management

## Setup Instructions
1. **Clone the repository:**
   ```bash
   git clone https://github.com/virgile-am/ConcurrencyLabApplication.git
   cd concurrency-lab
   ```

2. **Build the project:**
    - If using Maven:
      ```bash
      mvn clean install
      ```
 
    

3. **Run the application:**
  mvn spring-boot:run

## API Endpoints
- **Add a Task:**
    - `POST /api/tasks`
    - Request Body:
      ```json
      {
        "name": "Task Name",
        "status": "Task Status"
      }
      ```
    - Response: Returns the added task.

- **Get All Tasks:**
    - `GET /api/tasks`
    - Response: Returns a list of all tasks.

- **Get a Task by ID:**
    - `GET /api/tasks/{id}`
    - Response: Returns the task with the specified ID.

- **Update a Task:**
    - `PUT /api/tasks/{id}`
    - Request Body:
      ```json
      {
        "name": "Updated Task Name",
        "status": "Updated Task Status"
      }
      ```
    - Response: Returns the updated task.

- **Delete a Task:**
    - `DELETE /api/tasks/{id}`
    - Response: HTTP 204 No Content on successful deletion.

- **Compare Performance:**
    - `GET /api/tasks/comparePerformance`
    - Response: A string summarizing the performance comparison between concurrent and non-concurrent collections.

## Performance Benchmark
The application includes a benchmarking feature to compare the performance of concurrent and non-concurrent collections. This feature is accessible via the `/comparePerformance` endpoint.

## License
This project is licensed under the MIT License.
