package com.vsq.esignature.util;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.vsq.esignature.config.DocumentsResourceConfig;
import com.vsq.esignature.model.Person;
import freemarker.template.Template;
import freemarker.template.TemplateException;
//import gui.ava.html.image.generator.HtmlImageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * author: MohammadReza Ahmadi  15.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Service
public class PdfCreator {
    private static final Logger logger = LoggerFactory.getLogger(PdfCreator.class);

    private DocumentsResourceConfig documentsResourceConfig;
    private FreeMarkerConfigurer freeMarkerConfigurer;

//    private final String content_repo = "/home/java-projects/VSQ-LIVE/e-signature/content-repo/";

    public PdfCreator(DocumentsResourceConfig documentsResourceConfig, FreeMarkerConfigurer freeMarkerConfigurer) {
        this.documentsResourceConfig = documentsResourceConfig;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public String creatPersonPdf(Person person) throws IOException {
        String certificateImagePathName = getCertificateImagePathName(person);
        String certificatePdfPathName = getCertificatePdfPathName(person);

/*        String html = createHtmlForm(person);
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml(html);
        String imgSrc = certificateImagePathName.concat(documentsResourceConfig.getDOT_PNG());
        imageGenerator.saveAsImage(new File(imgSrc));
        String pdfDest = certificatePdfPathName.concat(documentsResourceConfig.getDOT_PDF());
        writeImageToPdf(imgSrc, pdfDest);
        return pdfDest;*/
        return null;
    }

    private String getPersonalImageAbsolutePathName(Person person){
        return documentsResourceConfig.getImageDocumentsWriteAbsolutePath().concat(person.getId().toString().concat(documentsResourceConfig.getUNDER_LINE()).concat(person.getNationalId().toString().trim()));
    }

    private String getCertificateImagePathName(Person person){
        return documentsResourceConfig.getImageDocumentsWritePath().concat(documentsResourceConfig.getUNDER_LINE().concat(person.getNationalId().toString().trim()));
    }

    private String getCertificatePdfPathName(Person person){
        return documentsResourceConfig.getPdfDocumentsWritePath().concat(documentsResourceConfig.getUNDER_LINE().concat(person.getNationalId().toString().trim()));
    }

    private String createHtmlForm(Person person) throws IOException {
        String certificateImagePathName = getCertificateImagePathName(person);
        String personalImagePathName = getPersonalImageAbsolutePathName(person);

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("form.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("logoUrl", documentsResourceConfig.getImageDocumentsReadAbsolutePath().concat(documentsResourceConfig.getVSQ_LOGO()));
        data.put("certificateLinkUrl", certificateImagePathName.concat(documentsResourceConfig.getDOT_PDF()));
        data.put("personalPhotoUrl", personalImagePathName.concat(documentsResourceConfig.getDOT_JPG()));
        data.put("person", person);
        try (StringWriter out = new StringWriter()) {
            template.process(data, out);
            String ftlContent = out.getBuffer().toString();
//            System.out.println(ftlContent);
            System.out.println("ftl parsed successfully.");
            out.flush();
            return ftlContent;
        } catch (TemplateException e) {
            logger.error("File writing using the Freemarker template engine failed!", e);
        }
        return null;
    }

    private void writeImageToPdf(String src, String dest) throws IOException {
        /** new-code*/
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        /** writer image in document*/
        ImageData imageData = ImageDataFactory.create(src);
        Image image = new Image(imageData);
        image.scaleToFit(PageSize.A5.getWidth(), PageSize.A5.getHeight());
        image.setFixedPosition(80, 250);
        document.add(image);


        /** Add anchor hypertext*/
        Paragraph paragraph = new Paragraph();
        Style style = new Style();
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        Color color = Color.convertRgbToCmyk(new DeviceRgb(60, 60, 60));
        style.setFont(font).setFontSize(14).setFontColor(color);
        paragraph.setFirstLineIndent(50);
        paragraph.setFixedLeading(450);
        PdfAction pdfAction = PdfAction.createURI("http://www.google.com");
        Link link = new Link("certificate-link-url", pdfAction);
        link.addStyle(style);
        link.getLinkAnnotation().setBorder(new PdfArray(new int[]{0, 0, 0}));
        paragraph.add(link);
        document.add(paragraph);
        document.close();
        writer.close();
        System.out.println("image writer to pdf successfully.");
    }
}
