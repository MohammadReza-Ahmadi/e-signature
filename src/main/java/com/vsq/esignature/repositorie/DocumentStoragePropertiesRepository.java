package com.vsq.esignature.repositorie;


import com.vsq.esignature.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentStoragePropertiesRepository extends JpaRepository<Document, Integer> {

//    @Query("Select a from DocumentStorageProperties a where user_id = ?1 and document_type = ?2")
//    DocumentStorageProperties checkDocumentByUserId(Long userId, String docType);


//    @Query("Select fileName from DocumnentStorageProperties a where user_id = ?1 and document_type = ?2")
//    String getUploadDocumentPath(Integer userId, String docType);

}