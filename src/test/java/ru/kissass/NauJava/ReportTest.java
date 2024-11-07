package ru.kissass.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kissass.NauJava.accessLayer.ReportRepository;
import ru.kissass.NauJava.businesLogic.ReportService;
import ru.kissass.NauJava.entity.Report;
import ru.kissass.NauJava.entity.ReportStatus;

@SpringBootTest
public class ReportTest {
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    public ReportTest(ReportService reportService, ReportRepository reportRepository) {
        this.reportService = reportService;
        this.reportRepository = reportRepository;
    }

    @Test
    void testProceedReport() throws InterruptedException {
        long id = reportService.createReport();
        String info = reportService.getInfoById(id);
        Assertions.assertNull(info);
        reportService.buildReport(id);
        Thread.sleep(2000);
        info = reportService.getInfoById(id);
        System.out.println(info);
        Assertions.assertNotNull(info);
    }

    @Test
    void testProceedNotWaiting() {
        long id = reportService.createReport();
        reportService.buildReport(id);
        Report report = reportRepository.findById(id).orElse(null);
        Assertions.assertNotNull(report);
        Assertions.assertEquals(ReportStatus.CREATED, report.getStatus());
    }
}
