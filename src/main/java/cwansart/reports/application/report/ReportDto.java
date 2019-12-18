package cwansart.reports.application.report;

import cwansart.reports.application.selection.SelectionDto;
import cwansart.reports.domain.report.Report;
import cwansart.reports.infrastructure.LocalDateAdapter;

import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReportDto {

    @NotNull
    @JsonbTypeAdapter(LocalDateAdapter.class)
    private LocalDate creationDate;

    @NotNull
    @JsonbTypeAdapter(LocalDateAdapter.class)
    private LocalDate processingDate;

    private List<SelectionDto> selection;

    private String freeText;

    public ReportDto() {
    }

    public ReportDto(Report report) {
        creationDate = report.getCreationDate().toLocalDate();
        processingDate = report.getProcessingDate().toLocalDate();
        freeText = report.getFreeText();

        selection = report.getSelection().stream()
            .map(SelectionDto::new)
            .collect(Collectors.toList());
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
    }

    public List<SelectionDto> getSelection() {
        return selection;
    }

    public void setSelection(List<SelectionDto> selection) {
        this.selection = selection;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }
}
