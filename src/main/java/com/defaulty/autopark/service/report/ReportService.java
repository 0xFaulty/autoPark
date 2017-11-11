package com.defaulty.autopark.service.report;

import java.io.File;
import java.util.Set;

public interface ReportService {

    File getFileFor(ReportType type);

    void generateReport(ReportType type);

    Set<ReportType> getAvailableReports();

}
