package com.vsq.esignature.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class QrGenerator {

    public static void main(String[] args) {
        Map hints = new HashMap();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String qrFileName = "qr.png";


        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File logoFile = ResourceUtils.getFile("classpath:qr/vsq-logo.png");
            File qrFile = ResourceUtils.getFile("classpath:qr/");

            // Create a qr code with the url as content and a size of 250x250 px
//            bitMatrix = writer.encode("https://chillyfacts.com", BarcodeFormat.QR_CODE, 250, 250, hints);
            String content = "This Is a Sample ConTenT !!";
            bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 500, 500, hints);

            //            bitMatrix = writer.encode("/home/java-projects/VSQ/qr-code-by-logo/output/hello.png", BarcodeFormat.QR_CODE, 250, 250, hints);
//            bitMatrix = writer.encode("https://chillyfacts.com", BarcodeFormat.QR_CODE, 706, 447, hints);
            MatrixToImageConfig config = new MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE);

            // Load QR image
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

            // Load logo image
//            File file = new File(logoUrl);
            BufferedImage logoImage = ImageIO.read(logoFile);

            // Calculate the delta height and width between QR code and logo
            int deltaHeight = qrImage.getHeight() - logoImage.getHeight();
            int deltaWidth = qrImage.getWidth() - logoImage.getWidth();

            // Initialize combined image
            BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) combined.getGraphics();

            // Write QR code to new image at position 0/0
            g.drawImage(qrImage, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            // Write logo into combine image at position (deltaWidth / 2) and
            // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
            // the same space for the logo to be centered
            g.drawImage(logoImage, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

            // Write combined image as PNG to OutputStream
            ImageIO.write(combined, "png", new File(qrFile.getPath()+"/"+qrFileName));
            System.out.println("done");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
