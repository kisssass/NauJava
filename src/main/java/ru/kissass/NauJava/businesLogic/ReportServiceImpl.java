package ru.kissass.NauJava.businesLogic;

import jakarta.transaction.Transactional;
import ru.kissass.NauJava.accessLayer.ReportRepository;
import ru.kissass.NauJava.accessLayer.TaskRepository;
import ru.kissass.NauJava.accessLayer.UserRepository;
import ru.kissass.NauJava.entity.Report;
import ru.kissass.NauJava.entity.ReportStatus;
import ru.kissass.NauJava.entity.Task;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;


public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    private final AtomicLong userCount;
    private final AtomicReference<List<Task>> tasks;

    public ReportServiceImpl(ReportRepository reportRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userCount = new AtomicLong(0);
        this.tasks = new AtomicReference<>();
    }

    @Override
    public String getInfoById(Long reportId) {
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report == null) {
            return null;
        }
        switch (report.getStatus()) {
            case CREATED -> {
                return "Created";
            }
            case COMPLETED -> {
                return report.getInfo();
            }
            default -> {
                return "Error";
            }
        }
    }

    @Transactional
    @Override
    public Long createReport() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        Report savedReport = reportRepository.save(report);

        return savedReport.getId();
    }

    @Transactional
    @Override
    public void buildReport(Long reportId) {
        CompletableFuture.supplyAsync(() -> {
            Report report = reportRepository.findById(reportId).orElse(null);
            long startTime = System.currentTimeMillis();
            if (report == null) {
                return null;
            }
            AtomicLong userCountElapsedTime = new AtomicLong();
            AtomicLong taskListElapsedTime = new AtomicLong();
            Thread userCountThread = new Thread(() -> {
                long userCountTime = System.currentTimeMillis();
                userCount.set(userRepository.count());
                userCountElapsedTime.set(System.currentTimeMillis() - userCountTime);
            });

            Thread taskListThread = new Thread(() -> {
                long taskListTime = System.currentTimeMillis();
                tasks.set((List<Task>) taskRepository.findAll());
                taskListElapsedTime.set(System.currentTimeMillis() - taskListTime);
            });

            userCountThread.start();

            taskListThread.start();

            try {

                userCountThread.join();
                taskListThread.join();

                long totalElapsedTime = System.currentTimeMillis() - startTime;
                String reportInfo = buildReportInfo(totalElapsedTime, userCountElapsedTime.get(),
                        taskListElapsedTime.get());
                report.setInfo(reportInfo);
                report.setStatus(ReportStatus.COMPLETED);
                reportRepository.save(report);
                return reportInfo;
            } catch (InterruptedException e) {
                report.setStatus(ReportStatus.ERROR);
                report.setInfo(e.getMessage());
                reportRepository.save(report);
                return null;
            }
        });
    }

    private String buildReportInfo(long totalTime, long userCountTime, long taskListTime) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div> Total time elapsed: ").append(totalTime).append(" ms").append("</div>\n");
        stringBuilder.append("<div> User count time elapsed: ").append(userCountTime).append(" ms</div>\n");
        stringBuilder.append("<div> User count: ").append(userCount.get()).append("</div>\n");
        stringBuilder.append("<div> Task list get elapsed: ").append(taskListTime).append(" ms</div>\n");
        stringBuilder.append("<table>");
        stringBuilder.append("""
                <tr>
                        <th>ID</th>
                        <th>Header</th>
                        <th>Content</th>
                        <th>Creation date</th>
                        <th></th>
                    </tr>""");
        for (Task task : tasks.get()) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>").append(task.getId()).append("</td>");
            stringBuilder.append("<td>").append(task.getTitle()).append("</td>");
            stringBuilder.append("<td>").append(task.getDescription()).append("</td>");
            stringBuilder.append("<td>").append(task.getDueDate()).append("</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }
}
