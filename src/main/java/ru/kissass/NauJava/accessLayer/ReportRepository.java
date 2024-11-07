package ru.kissass.NauJava.accessLayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.kissass.NauJava.entity.Report;


@RepositoryRestResource(path = "reports")
public interface ReportRepository extends CrudRepository<Report, Long> {
    @Query("SELECT r.content from Report r")
    String getContentById(Long id);
}
