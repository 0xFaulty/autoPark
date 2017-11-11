package com.defaulty.autopark.controller;

import com.defaulty.autopark.service.report.ReportService;
import com.defaulty.autopark.service.report.ReportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/report/{reportName}", method = RequestMethod.GET)
    public String generateReport(@PathVariable int reportName) {
        switch (reportName) {
            case 1:
                reportService.generateReport(ReportType.CAR_OWNERS);
                break;
            case 2:
                reportService.generateReport(ReportType.TOP_TIME);
                break;
        }

        return "redirect:/report";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String showPage(Model model) {

        model.addAttribute("reportSet", reportService.getAvailableReports());

        return "report/report";
    }

    @RequestMapping(value = "/dloadreport/{reportName}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getFile(@PathVariable String reportName) throws IOException {
        File file = reportService.getFileFor(ReportType.valueOf(reportName));

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.TEXT_HTML);
        respHeaders.setContentLength(file.length());
        respHeaders.setContentDispositionFormData("attachment", file.getName());

        InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
        return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
    }

}
