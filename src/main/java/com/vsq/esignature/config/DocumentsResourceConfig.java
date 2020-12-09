package com.vsq.esignature.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * author: MohammadReza Ahmadi  15.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Configuration
public class DocumentsResourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DocumentsResourceConfig.class);
    private final String prefixWritePath = "src/main/resources/";
    /**
     * readable fields
     */
    private final String VSQ_LOGO = "vsq-logo.jpeg";
    private final String DOT_PNG = ".png";
    private final String DOT_JPG = ".jpg";
    private final String DOT_PDF = ".pdf";
    private final String UNDER_LINE = "_";
    /**
     * inner fields
     */
    private final String SLASH = "/";
    private final String IMAGES = "images";
    private final String VIDEOS = "videos";
    private final String PDFS = "pdfs";
    @Value("classpath:documents")
    private Resource documentsPath;
    private String url;
    private String absoluteUrl;

    @PostConstruct
    public Resource getDocumentsPath() {
        url = ((ClassPathResource) documentsPath).getPath().concat(SLASH);
        try {
            absoluteUrl = documentsPath.getURL().getPath();
            absoluteUrl = absoluteUrl.split("target")[0].concat(getDocumentsWritePath());
        } catch (IOException e) {
            logger.error("Error in loading resource path!");
        }
        return documentsPath;
    }

    public String getImageDocumentsReadPath() {
        return url.concat(IMAGES).concat(SLASH);
    }

    public String getImageDocumentsReadAbsolutePath() {
        return absoluteUrl.concat(SLASH).concat(IMAGES).concat(SLASH);
    }

    public String getDocumentsWritePath() {
        return prefixWritePath.concat(url);
    }

    public String getImageDocumentsWritePath() {
        return prefixWritePath.concat(url).concat(IMAGES).concat(SLASH);
    }

    public String getImageDocumentsWriteAbsolutePath() {
        return absoluteUrl.concat(SLASH).concat(IMAGES).concat(SLASH);
    }

    public String getVideoDocumentsReadPath() {
        return url.concat(VIDEOS).concat(SLASH);
    }

    public String getVideoDocumentsWritePath() {
        return prefixWritePath.concat(url).concat(VIDEOS).concat(SLASH);
    }

    public String getPdfDocumentsReadPath() {
        return url.concat(PDFS).concat(SLASH);
    }

    public String getPdfDocumentsWritePath() {
        return prefixWritePath.concat(url).concat(PDFS).concat(SLASH);
    }

    public String getPrefixWritePath() {
        return prefixWritePath;
    }

    public String getVSQ_LOGO() {
        return VSQ_LOGO;
    }

    public String getDOT_PNG() {
        return DOT_PNG;
    }

    public String getDOT_JPG() {
        return DOT_JPG;
    }

    public String getDOT_PDF() {
        return DOT_PDF;
    }

    public String getUNDER_LINE() {
        return UNDER_LINE;
    }
}
