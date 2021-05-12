package com.tulisova.parking.service.pdf;

import net.sf.jasperreports.engine.*;

import javax.servlet.http.*;
import java.io.*;

public interface JasperReportService {
    void exportReport(Long reservationId, HttpServletResponse response) throws IOException, JRException;
    void exportReport(String filteredDate, HttpServletResponse response) throws IOException, JRException;
}
