package com.vsq.esignature.exception;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */


public class DocumentStorageException extends RuntimeException {

    public DocumentStorageException(String message) {
        super(message);
    }

        
    public DocumentStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}