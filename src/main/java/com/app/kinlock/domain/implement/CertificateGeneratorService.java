package com.app.kinlock.domain.implement;

import com.app.kinlock.presentation.pojo.CertificateData;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.convert.out.pdf.viaXSLFO.Conversion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CertificateGeneratorService {

    private final File template;

    public CertificateGeneratorService(File template) {
        this.template = template;
    }

    public byte[] generateCertificatePdf(CertificateData data) throws Exception {
        // 1. load template
        WordprocessingMLPackage pkg = WordprocessingMLPackage.load(template);

        // 2. variables to substitute
        Map<String, String> vars = new HashMap<>();
        vars.put("NOMBRE_CLIENTE", data.getNombreCliente());
        vars.put("NOMBRE_SEGURO", data.getNombreSeguro());
        vars.put("DESCRIPCION_VEHICULO", data.getDescripcionVehiculo());
        vars.put("FECHA_EMISION", data.getFechaEmision());
        vars.put("FECHA_INICIO", data.getFechaInicio());
        vars.put("FECHA_FIN", data.getFechaFin());
        vars.put("FECHA_INICIO_LARGA", data.getFechaInicioLarga());

        // 3. replace variables (docx4j 8.x API)
        MainDocumentPart doc = pkg.getMainDocumentPart();
        doc.variableReplace(vars);

        // 4. convert to PDF
        return toPdf(pkg);
    }

    private byte[] toPdf(WordprocessingMLPackage pkg) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfConversion pdfConv = new Conversion(pkg);   // 8.x XSL-FO converter
        pdfConv.output(bos, null);
        return bos.toByteArray();
    }
}