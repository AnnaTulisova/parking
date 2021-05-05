package com.tulisova.parking.service.impl;

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

@Service
@RequiredArgsConstructor
public class JasperReportServiceImpl implements JasperReportService {
    private final ReservationService reservationService;

    @Override
    public void exportReport(Long reservationId, HttpServletResponse response) throws IOException, JRException {
        Reservation reservation = reservationService.findById(reservationId);
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"ticket.pdf\""));

        File file = ResourceUtils.getFile("classpath:ticket.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(new Reservation[]{reservation}));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String,Object>(), dataSource);

        //String fileName = "C:\\Users\\atuli\\Desktop\\Report\\" + "талон № "+ reservation.getId() + ".pdf";
        OutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    }

}