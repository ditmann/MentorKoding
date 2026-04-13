import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    @JsonProperty("tasks")
    private String title;

    @JsonProperty("task_description")
    private String description;

    private int points;
    private boolean done;

    @JsonProperty("date_due")
    private String dateDue;

    public Task() {}

    public Task(String title, int points) {
        this.title = title;
        this.points = points;
        this.done = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    @Override
    public String toString() {
        return "Task{title='" + title + "', points=" + points + ", done=" + done + "}";
    }
}