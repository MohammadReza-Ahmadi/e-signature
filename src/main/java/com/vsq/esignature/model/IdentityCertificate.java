package com.vsq.esignature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Data
@Entity
@Table(name = "IDENTITY_CERTIFICATE")
public class IdentityCertificate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long certificateNumber;
    private String certificateSeries;
    private String certificateSerial;
    private String placeOfBirth;
    private Date dateOfBirth;
    private Date dateOfIssue;
    private String codeOfIssue;
    private String placeOfIssue;
    @JsonIgnore
    @OneToOne(mappedBy = "identityCertificate")
    private Person person;

}
