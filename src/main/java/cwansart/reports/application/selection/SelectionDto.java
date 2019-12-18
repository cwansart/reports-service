package cwansart.reports.application.selection;

import cwansart.reports.domain.selection.Selection;

import javax.validation.constraints.NotNull;

public class SelectionDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    public SelectionDto() {
    }

    public SelectionDto(Selection selection) {
        this.id = selection.getId();
        this.title = selection.getTitle();
        this.description = selection.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
