package com.vsq.esignature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Data
@Entity
@Table(name = "DOCUMENT")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long documentId;
    private Long UserId;
    private String documentHashCode;
    private String userHashCode;
    private String fileName;
    private String documentType;
    private String documentFormat;
    private String documentPath;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}