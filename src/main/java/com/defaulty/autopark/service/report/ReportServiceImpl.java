package com.defaulty.autopark.service.report;

import com.defaulty.autopark.files.FileCRUD;
import com.defaulty.autopark.model.Auto;
import com.defaulty.autopark.model.AutoPersonnel;
import com.defaulty.autopark.model.Journal;
import com.defaulty.autopark.model.Route;
import com.defaulty.autopark.service.auto.AutoService;
import com.defaulty.autopark.service.journal.JournalService;
import com.defaulty.autopark.service.personnel.AutoPersonnelService;
import com.defaulty.autopark.service.routes.RouteService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private FileCRUD fileCRUD;

    @Autowired
    private AutoService autoService;

    @Autowired
    private AutoPersonnelService autoPersonnelService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private RouteService routeService;

    @PersistenceContext
    private EntityManager entityManager;

    private static HashMap<ReportType, File> reportHash = new HashMap<>();

    @Value("${table.report.path}")
    private String pathString;

    private static File path;

//    @Resource(name="myProperties")
//    private Properties myProperties;

    @PostConstruct
    public void init() {
        path = new File(System.getenv("TEMP"));
        if (pathString != null) {
            File file = new File(pathString);
            if (file.exists()) path = file;
        }

        recheckReports();
    }

    @Override
    public File getFileFor(ReportType type) {
        return reportHash.get(type);
    }

    @Override
    public void generateReport(ReportType type) {
        switch (type) {
            case CAR_OWNERS:
                getTD();
                break;
            case TOP_TIME:
                getTT();
                break;
        }
    }

    @Override
    public Set<ReportType> getAvailableReports() {
        return reportHash.keySet();
    }

    @Override
    public void recheckReports() {
        reportHash.clear();
        File[] files = path.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                try {
                    String name = FilenameUtils.getBaseName(file.getName());
                    ReportType type = ReportType.valueOf(name);
                    reportHash.putIfAbsent(type, file);
                } catch (IllegalArgumentException ignored) {
                }
            }
        }
    }

    private void getTD() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Report about autos owners:\n\n");

            for (AutoPersonnel personnel : autoPersonnelService.list()) {
                sb.append("Personnel: ")
                        .append(personnel.getFirst_name()).append(" ").append(personnel.getLast_name());
                Set<Auto> autoSet = personnel.getAutos();
                for (Auto auto : autoSet) {
                    sb.append("\n\t Auto: ")
                            .append(auto.getColor()).append(" ")
                            .append(auto.getMark()).append(" ")
                            .append(auto.getNum()).append(" ");
                }
                sb.append("\n\n");
            }

            File file = new File(path.getPath() + "/" + ReportType.CAR_OWNERS.name() + ".txt");
            fileCRUD.write(file.getPath(), sb.toString());

            reportHash.put(ReportType.CAR_OWNERS, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getTT() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Report about routes records:\n\n");

            List<Journal> top = entityManager.createQuery(
                    "SELECT c FROM Journal c WHERE (c.time_in - c.time_out) " +
                            "IN (SELECT min(cc.time_in - cc.time_out)" +
                            "FROM Journal as cc GROUP BY route) " +
                            "AND c.route " +
                            "IN (SELECT route FROM Journal as ccc GROUP BY route)", Journal.class)
                    .getResultList();

            for (Journal journal : top) {
                if (!top.isEmpty()) {
                    long time_in = journal.getTime_in().getTime();
                    long time_out = journal.getTime_out().getTime();
                    Time best_time = new Time(time_in - time_out);
                    Auto auto = journal.getAuto();
                    AutoPersonnel personnel = auto.getAutoPersonnel();
                    Route route = journal.getRoute();

                    sb.append("\tRoute: '").append(route.getName())
                            .append("'\n\tBest time: ").append(best_time)
                            .append("\n\tAuto number: ").append(auto.getNum())
                            .append("\n\tPersonnel: ").append(personnel.getFirst_name())
                            .append(" ").append(personnel.getLast_name()).append("\n\n");
                }
            }

            File file = new File(path.getPath() + "/" + ReportType.TOP_TIME.name() + ".txt");
            fileCRUD.write(file.getPath(), sb.toString());

            reportHash.put(ReportType.TOP_TIME, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
