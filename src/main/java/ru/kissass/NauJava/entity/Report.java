package ru.kissass.NauJava.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue
    private Long reportId;

    @Column(columnDefinition = "TEXT")
    private String info;

    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    public Long getId() {
        return reportId;
    }

    public void setId(Long id) {
        this.reportId = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ReportStatus getStatus() {
        return reportStatus;
    }

    public void setStatus(ReportStatus status) {
        this.reportStatus = status;
    }
}
