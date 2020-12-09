package com.vsq.esignature.service;


import com.vsq.esignature.config.DocumentsResourceConfig;
import com.vsq.esignature.enumeration.DocumentType;
import com.vsq.esignature.exception.DocumentStorageException;
import com.vsq.esignature.model.Person;
import com.vsq.esignature.repositorie.DocumentStoragePropertiesRepository;
import com.vsq.esignature.util.PdfCreator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import /*
            Path path = Paths.get(CONTENT_REPO + fileName).toAbsolutePath().normalize();
            Path targetLocation = path.resolve(CONTENT_REPO + fileName);
*/java.nio.file.StandardCopyOption;

import static com.vsq.esignature.enumeration.DocumentType.IMAGE;

/**
 * author: MohammadReza Ahmadi  .08.20
 * email: m.reza79ahmadi@gmail.com
 */


@Service
public class DocumentService {

    private final PdfCreator pdfCreator;
    private final DocumentsResourceConfig documentsResourceConfig;
    private final DocumentStoragePropertiesRepository docStorageRepo;
    private PersonService personService;

    public DocumentService(PersonService personService, PdfCreator pdfCreator, DocumentStoragePropertiesRepository docStorageRepo, DocumentsResourceConfig documentsResourceConfig) {
        this.personService = personService;
        this.pdfCreator = pdfCreator;
        this.docStorageRepo = docStorageRepo;
        this.documentsResourceConfig = documentsResourceConfig;
    }


/*    private final DocumentStorageProperties documentStorageProperties;
     private final Path fileStorageLocation;

    public DocumentStorageService(DocumentStoragePropertiesRepository docStorageRepo, DocumentStorageProperties fileStorageProperties) {
        this.documentStorageProperties = fileStorageProperties;
        this.docStorageRepo = docStorageRepo;

        this.fileStorageLocation = Paths.get(fileStorageProperties.getDocumentPath()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new DocumentStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }*/


    public String saveFile(MultipartFile file, Long userId, String docType) {
        /** Normalize file name*/
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        String fileName = "";
        try {
            /** Check if the file's name contains invalid characters*/
            if (originalFileName.contains("..")) {
                throw new DocumentStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
            }

            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            } catch (Exception e) {
                fileExtension = "";
            }

            fileName = userId + "_" + docType + fileExtension;
            /**Copy file to the target location (Replacing existing file with the same name)*/

            Path targetLocation = resolveWriteFilePath(fileName, IMAGE);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            /** call pdfCreator */
            Person person = personService.get(userId);
            fileName = pdfCreator.creatPersonPdf(person);

/*            DocumentStorageProperties doc = docStorageRepo.checkDocumentByUserId(userId, docType);
            if (doc != null) {
                doc.setDocumentFormat(file.getContentType());
                doc.setFileName(fileName);
                docStorageRepo.save(doc);

            } else {
                DocumentStorageProperties newDoc = new DocumentStorageProperties();
                newDoc.setUserId(userId);
                newDoc.setDocumentFormat(file.getContentType());
                newDoc.setFileName(fileName);
                newDoc.setDocumentType(docType);
                docStorageRepo.save(newDoc);
            }*/

            return fileName;

        } catch (IOException ex) {
            throw new DocumentStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }


    public ResponseEntity<Resource> downloadFile(Integer userId, String docType, String fileName, HttpServletRequest request) {
        Resource resource = null;
        if (fileName != null && !fileName.isEmpty()) {
            try {
                resource = loadFileAsResource(fileName);

            } catch (Exception e) {
                e.printStackTrace();
            }

            /** Try to determine file's content type*/
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            } catch (IOException ex) {
                //logger.info("Could not determine file type.");
            }

            /** Fallback to the default content type if type could not be determined*/
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    private Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = resolveReadFilePath(fileName, IMAGE);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;

            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }

        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    private Path resolveReadFilePath(String fileName, DocumentType documentType) {
        switch (documentType) {
            case IMAGE:
                return Path.of(documentsResourceConfig.getImageDocumentsReadPath().concat(fileName));
            case VIDEO:
                return Path.of(documentsResourceConfig.getVideoDocumentsReadPath().concat(fileName));
            case PDF:
                return Path.of(documentsResourceConfig.getPdfDocumentsReadPath().concat(fileName));
            default:
                throw new IllegalStateException("Unexpected value: " + documentType);
        }
    }

    private Path resolveWriteFilePath(String fileName, DocumentType documentType) {
        switch (documentType) {
            case IMAGE:
//                return Path.of(documentsResourceConfig.getPrefixWritePath().concat(documentsResourceConfig.getImageDocumentsReadPath()).concat(fileName));
                return Path.of(documentsResourceConfig.getImageDocumentsWritePath().concat(fileName));
            case VIDEO:
                return Path.of(documentsResourceConfig.getPrefixWritePath().concat(documentsResourceConfig.getVideoDocumentsReadPath()).concat(fileName));
            case PDF:
                return Path.of(documentsResourceConfig.getPrefixWritePath().concat(documentsResourceConfig.getPdfDocumentsReadPath()).concat(fileName));
            default:
                throw new IllegalStateException("Unexpected value: " + documentType);
        }
    }
}