package cwansart.reports.domain.report;

import cwansart.reports.application.report.CreateReportDto;
import cwansart.reports.domain.selection.Selection;
import cwansart.reports.domain.selection.SelectionRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestScoped
public class ReportService {

    @Inject
    private ReportRepository reportRepository;

    @Inject
    private SelectionRepository selectionRepository;

    public LocalDate createReport(CreateReportDto dto) {
        Report report = new Report();
        report.setCreationDate(Date.valueOf(dto.getCreationDate()));
        report.setProcessingDate(Date.valueOf(LocalDate.now(ZoneId.of("Europe/Berlin"))));
        report.setFreeText(dto.getFreeText());

        if (dto.getSelectionIds() != null) {
            List<Selection> selections = dto.getSelectionIds().stream()
                .map(id -> selectionRepository.getSelection(id))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            report.setSelection(selections);
        }

        Date creationDate = reportRepository.createReport(report);

        return creationDate.toLocalDate();
    }

    public boolean updateReport(CreateReportDto dto) {
        Report report = reportRepository.getReport(Date.valueOf(dto.getCreationDate()));

        if (report == null) {
            return false;
        }

        report.setProcessingDate(Date.valueOf(LocalDate.now(ZoneId.of("Europe/Berlin"))));
        report.setFreeText(dto.getFreeText());

        if (dto.getSelectionIds() != null) {
            List<Selection> selections = dto.getSelectionIds().stream()
                .map(id -> selectionRepository.getSelection(id))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            report.setSelection(selections);
        } else {
            dto.setSelectionIds(null);
        }

        reportRepository.updateReport(report);

        return true;
    }
}
