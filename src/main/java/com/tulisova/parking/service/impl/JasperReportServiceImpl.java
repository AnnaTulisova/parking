package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.extra.*;
import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.pdf.*;
import lombok.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.engine.util.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class JasperReportServiceImpl implements JasperReportService {
    private final ReservationService reservationService;

    @Override
    public void exportReport(Long reservationId, HttpServletResponse response) throws IOException, JRException {
        Reservation reservation = reservationService.findById(reservationId);
        ReservationExtra reservationForPdf = new ReservationExtra(reservation);

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"ticket.pdf\""));
        File file = ResourceUtils.getFile("classpath:ticket.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(new ReservationExtra[]{reservationForPdf}));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String,Object>(), dataSource);

        OutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    }

    @Override
    public void exportReport(String filteredDate, HttpServletResponse response) throws IOException, JRException {
        Collection<Reservation> reservations;
        if(filteredDate.isEmpty()) {
            reservations = reservationService.findAll();
        } else {
            reservations = reservationService.findAllByStartDateTime(filteredDate);
        }
        Collection<ReservationExtra> pdfReservations = reservations.stream().map(ReservationExtra::new).collect(Collectors.toList());

        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"reservations.pdf\""));
        File file = ResourceUtils.getFile("classpath:reservation_list.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRDataSource dataSource = new JRBeanCollectionDataSource(pdfReservations);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String,Object>(), dataSource);

        OutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

    }
}
