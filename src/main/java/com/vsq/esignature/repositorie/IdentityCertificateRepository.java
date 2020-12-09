package com.vsq.esignature.repositorie;

import com.vsq.esignature.model.IdentityCertificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityCertificateRepository extends CrudRepository<IdentityCertificate, Long> {

}
