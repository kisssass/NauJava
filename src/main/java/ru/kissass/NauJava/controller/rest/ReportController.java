package ru.kissass.NauJava.controller.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import ru.kissass.NauJava.DTO.ReportDTO;
import ru.kissass.NauJava.businesLogic.ReportService;

@RestController
@RequestMapping("/user/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public String getReportInfo(@PathVariable long id) {
        return reportService.getInfoById(id);
    }

    @GetMapping("/report")
    @ResponseBody
    public String createReport() {
        Long createdReportId = reportService.createReport();
        reportService.buildReport(createdReportId);
        return "{ id: " + createdReportId + "}";
    }

    @PostMapping
    public ResponseEntity<ReportDTO> saveReport() {
        Long id = reportService.createReport();
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setReportId(id);
        return ResponseEntity.ok(reportDTO);
    }



}
