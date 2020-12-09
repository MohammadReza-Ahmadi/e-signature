package com.vsq.esignature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Data
@Entity
@Table(name = "contract")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer phoneCode;
    private Integer primaryPhoneNumber;
    private Integer secondaryPhoneNumber;
    private Integer workPhoneNumber;
    private Integer faxNumber;
    private String mobileNumber;
    private String postalCode;
    private String cityOfResidence;
    private String streetOfResidence;
    private String cityOfWorkplace;
    private String streetOfWorkplace;
    private String emailAddress;
    @JsonIgnore
    @OneToOne(mappedBy = "contact")
    private Person person;

}
