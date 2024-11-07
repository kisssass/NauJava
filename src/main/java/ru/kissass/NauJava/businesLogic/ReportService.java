package ru.kissass.NauJava.businesLogic;

import ru.kissass.NauJava.entity.Report;

public interface ReportService {
    String getInfoById(Long reportId);

    Long createReport();

    void buildReport(Long reportId);
}
