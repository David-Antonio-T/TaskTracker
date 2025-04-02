import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private String id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int position;

    public Task() {
    }

    public Task(String id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, int position) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.position = position;
    }


    public Task(String description, TaskStatus status) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.position = 0;//default value
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        setUpdatedAt(LocalDateTime.now());
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        setUpdatedAt(LocalDateTime.now());
        this.status = status;
    }

    @Override
    public String toString() {
        return position + " " + description +
                " " + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt;
    }

}

