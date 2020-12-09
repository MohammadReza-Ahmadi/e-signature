

-- ********* H2 db import data ************************************
-- identity_certificate insertion
-- INSERT into IDENTITY_CERTIFICATE (CERTIFICATE_NUMBER, CERTIFICATE_SERIAL, CERTIFICATE_SERIES, CODE_OF_ISSUE,DATE_OF_BIRTH, DATE_OF_ISSUE, PLACE_OF_BIRTH, PLACE_OF_ISSUE) values (3365,'12L','123456','0045',sysdate,sysdate,'tehran','shiraz');


-- contact insertion
-- insert into contact(CITY_OF_RESIDENCE, CITY_OF_WORKPLACE,EMAIL_ADDRESS,FAX_NUMBER,MOBILE_NUMBER,PHONE_CODE,POSTAL_CODE,PRIMARY_PHONE_NUMBER,SECONDARY_PHONE_NUMBER,STREET_OF_RESIDENCE,STREET_OF_WORKPLACE,WORK_PHONE_NUMBER) values ('tabriz','esfehan','rraall@gmail.com',123456,'09123456987','021','123456789','12345678','987654321','street-R','street-W','74125896');

-- person insertion
-- INSERT INTO PERSON(FIRST_NAME, LAST_NAME, FATHER_NAME,GENDER, NATIONAL_ID, LICENSE_NUMBER, PASSPORT_NUMBER, PROFESSION,GRADE_OF_EDUCATION, SIGNATURE_URL, DESCRIPTION,IDENTITY_CERTIFICATE_ID,contact_id) VALUES ('ali', 'kaveh', 'mansour','MALE', 123456, '123456', '021-654321', 'karmand', 'license', 'www', 'some description',1,1);


-- document insertion
-- insert into document(USER_ID,DOCUMENT_FORMAT,DOCUMENT_HASH_CODE,DOCUMENT_ID,DOCUMENT_PATH,DOCUMENT_TYPE,FILE_NAME,USER_HASH_CODE,person_id) values (100,'png','document-hash-code',1001,'/home/images/','signature-image','my-file-name','user-hash-code',1);
-- insert into document(USER_ID,DOCUMENT_FORMAT,DOCUMENT_HASH_CODE,DOCUMENT_ID,DOCUMENT_PATH,DOCUMENT_TYPE,FILE_NAME,USER_HASH_CODE,person_id) values (200,'jpg','document-hash-code-2',2001,'/home/images/','signature-image-2','my-file-name-2','user-hash-code-2',1);


-- ********* Postgres db import data ************************************
-- identity_certificate insertion
INSERT into IDENTITY_CERTIFICATE (CERTIFICATE_NUMBER, CERTIFICATE_SERIAL, CERTIFICATE_SERIES, CODE_OF_ISSUE,DATE_OF_BIRTH, DATE_OF_ISSUE, PLACE_OF_BIRTH, PLACE_OF_ISSUE) values (3365,'12L','123456','0045',now(),now(),'tabriz','shiraz');


-- contact insertion
insert into contact(CITY_OF_RESIDENCE, CITY_OF_WORKPLACE,EMAIL_ADDRESS,FAX_NUMBER,MOBILE_NUMBER,PHONE_CODE,POSTAL_CODE,PRIMARY_PHONE_NUMBER,SECONDARY_PHONE_NUMBER,STREET_OF_RESIDENCE,STREET_OF_WORKPLACE,WORK_PHONE_NUMBER) values ('tabriz','esfehan','rraall@gmail.com',123456,'09123456987','021','123456789','12345678','987654321','street-R','street-W','74125896');

-- person insertion
INSERT INTO PERSON(FIRST_NAME, LAST_NAME, FATHER_NAME,GENDER, NATIONAL_ID, LICENSE_NUMBER, PASSPORT_NUMBER, PROFESSION,GRADE_OF_EDUCATION, SIGNATURE_URL, DESCRIPTION,IDENTITY_CERTIFICATE_ID,contact_id) VALUES ('ali', 'kaveh', 'mansour','MALE', 123456, '123456', '021-654321', 'karmand', 'license', 'www', 'some description',1,1);


-- document insertion
insert into document(USER_ID,DOCUMENT_FORMAT,DOCUMENT_HASH_CODE,DOCUMENT_ID,DOCUMENT_PATH,DOCUMENT_TYPE,FILE_NAME,USER_HASH_CODE,person_id) values (100,'png','document-hash-code',1001,'/home/images/','signature-image','my-file-name','user-hash-code',1);
insert into document(USER_ID,DOCUMENT_FORMAT,DOCUMENT_HASH_CODE,DOCUMENT_ID,DOCUMENT_PATH,DOCUMENT_TYPE,FILE_NAME,USER_HASH_CODE,person_id) values (200,'jpg','document-hash-code-2',2001,'/home/images/','signature-image-2','my-file-name-2','user-hash-code-2',1);



