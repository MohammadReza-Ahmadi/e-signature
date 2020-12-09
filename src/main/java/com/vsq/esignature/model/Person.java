package com.vsq.esignature.model;

import com.vsq.esignature.enumeration.AppEnums;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Data
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Long nationalId;
    private String gradeOfEducation;
    private String profession;
    private String passportNumber;
    private String licenseNumber;
    private String signatureUrl;
    private String description;
    @Enumerated(EnumType.STRING)
    private AppEnums.GENDER gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identity_certificate_id", referencedColumnName = "id")
    private IdentityCertificate identityCertificate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Document> documentList;

    @Override
    public String toString(){
        return "person";
    }
}
