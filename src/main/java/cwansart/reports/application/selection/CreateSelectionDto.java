package cwansart.reports.application.selection;

import javax.validation.constraints.NotNull;

public class CreateSelectionDto {

    @NotNull
    private String title;

    @NotNull
    private String description;

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

    @Override
    public String toString() {
        return "CreateSelectionDto{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
