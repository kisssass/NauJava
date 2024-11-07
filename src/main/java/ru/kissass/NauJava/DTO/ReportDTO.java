package ru.kissass.NauJava.DTO;

import ru.kissass.NauJava.entity.ReportStatus;

public class ReportDTO {
    private Long reportId;
    private ReportStatus status;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long id) {
        this.reportId = reportId;
    }
}
