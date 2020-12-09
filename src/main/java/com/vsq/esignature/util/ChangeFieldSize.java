package com.vsq.esignature.util;

/**
 * author: MohammadReza Ahmadi  12.08.20
 * email: m.reza79ahmadi@gmail.com
 */



import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfReader;

import java.io.File;

public class ChangeFieldSize {
    public static final String DEST = "/home/vsq-docs/first-form-filled.pdf";

    public static final String SRC = "/home/vsq-docs/first-form.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new ChangeFieldSize().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
//        PdfFormField field = form.getField("Name");
//        field.setValue(value);
        form.getField("docName").setValue("BankMellatLoanContract036.pdf");
        form.getField("docId").setValue("f00f0fada7330hhfga4455slsfbs8874bs646");
//        form.getField("fullName").setValue("احمد حسینی");
        form.getField("fullName").setValue("Ahamad Hoseini");

        pdfDoc.close();
    }
}