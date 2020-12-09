package com.vsq.esignature.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Getter
@Setter
@NoArgsConstructor
public class UploadFileResponse implements Serializable {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
