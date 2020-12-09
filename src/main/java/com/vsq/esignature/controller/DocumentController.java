package com.vsq.esignature.controller;

import com.vsq.esignature.service.DocumentService;
import com.vsq.esignature.util.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload-file")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
                                         @RequestParam("userId") Long UserId,
                                         @RequestParam("docType") String docType) {
        String fileName = documentService.saveFile(file, UserId, docType);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/documents/download-file/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping("/download-file/{fileName}")
    public ResponseEntity<Resource>  downloadFile(@PathVariable String fileName,HttpServletRequest request) {
        return documentService.downloadFile(0,"docType",fileName,request);
    }


}
