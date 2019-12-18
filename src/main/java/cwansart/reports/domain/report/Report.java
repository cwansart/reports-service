package cwansart.reports.domain.report;

import cwansart.reports.domain.selection.Selection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private Date creationDate;

    @NotNull
    private Date processingDate;

    @OneToMany
    private List<Selection> selection;

    private String freeText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public List<Selection> getSelection() {
        return selection;
    }

    public void setSelection(List<Selection> selection) {
        this.selection = selection;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    @Override
    public String toString() {
        return "Report{" +
            "id=" + id +
            ", creationDate=" + creationDate +
            ", processingDate=" + processingDate +
            ", selection=" + selection +
            ", freeText='" + freeText + '\'' +
            '}';
    }
}
