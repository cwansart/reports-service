package cwansart.reports.application.report;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class CreateReportDto {

    @NotNull
    private LocalDate creationDate;

    private List<Long> selectionIds;

    private String freeText;

    public CreateReportDto() {
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Long> getSelectionIds() {
        return selectionIds;
    }

    public void setSelectionIds(List<Long> selectionIds) {
        this.selectionIds = selectionIds;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    @Override
    public String toString() {
        return "CreateReportDto{" +
            "creationDate=" + creationDate +
            ", selectionIds=" + selectionIds +
            ", freeText='" + freeText + '\'' +
            '}';
    }
}
