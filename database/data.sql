select * from bbop_code;
delete from bbop_code;

-- Application Types
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_TYPE', 'Application Type', 'C', 'Barangay Clearance', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_TYPE', 'Application Type', 'R', 'Certificate of Residency', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_TYPE', 'Application Type', 'I', 'Certificate of Indigency', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

-- Purpose of the Application
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC1', 'Water Installation', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC2', 'Electric Installation', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC3', 'Bank Requirement', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC4', 'Loan', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC5', 'Medical Assistance', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC6', 'Financial Assistance', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC7', 'Police Clearance Requirement', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC8', 'Employment', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC9', 'Scholarship', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_C', 'Purpose of the Application', 'PC10', 'Philippine Airforce Schooling', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_R', 'Purpose of the Application', 'PI1', 'Scholarship', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_R', 'Purpose of the Application', 'PI2', 'Financial Assistance', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_R', 'Purpose of the Application', 'PI3', 'Medical Assistance', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_R', 'Purpose of the Application', 'PI4', 'Burial Assistance', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_I', 'Purpose of the Application', 'PR1', 'Motorcycle Loan', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_I', 'Purpose of the Application', 'PR2', 'House Loan', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_I', 'Purpose of the Application', 'PR3', 'Solo Parent', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_I', 'Purpose of the Application', 'PR4', 'Senior Citizen', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_PURPOSE_I', 'Purpose of the Application', 'PR5', 'National ID', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

-- Application Statuses
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_STATUS', 'Application Status', 'D', 'Draft', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_STATUS', 'Application Status', 'O', 'Open', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_STATUS', 'Application Status', 'R', 'Rejected', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_STATUS', 'Application Status', 'A', 'Approved', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_STATUS', 'Application Status', 'C', 'Closed', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_STATUS', 'Application Status', 'W', 'Withdrawn', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'APPL_STATUS', 'Application Status', 'X', 'Deleted', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

-- Payment Modes
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'PAYMENT_MODE', 'Mode of Payment', 'OTC', 'Over-the-Counter', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'PAYMENT_MODE', 'Mode of Payment', 'GC', 'GCash', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'PAYMENT_MODE', 'Mode of Payment', 'CC', 'Credit/Debit Card', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DOCUMENT_TYPE', 'Document Type', 'DT1', 'National ID', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DOCUMENT_TYPE', 'Document Type', 'DT2', 'Postal ID', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DOCUMENT_TYPE', 'Document Type', 'DT3', 'Birth Certificate', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DOCUMENT_TYPE', 'Document Type', 'DT4', 'Passport', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DOCUMENT_TYPE', 'Document Type', 'DT5', 'SSS/UMID', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DOCUMENT_TYPE', 'Document Type', 'DT6', 'Driver''s License', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DOCUMENT_TYPE', 'Document Type', 'DT7', 'Voter''s ID', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DOCUMENT_TYPE', 'Document Type', 'DT8', 'Others', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'GENDER', 'Gender', 'M', 'Male', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'GENDER', 'Gender', 'F', 'Female', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'CIVIL_STATUS', 'Civil Status', 'S', 'Single', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'CIVIL_STATUS', 'Civil Status', 'M', 'Married', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'CIVIL_STATUS', 'Civil Status', 'D', 'Divorced', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'CIVIL_STATUS', 'Civil Status', 'W', 'Widowed', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'STREET', 'Street', 'ST1', 'Bagonpook Ave.', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'STREET', 'Street', 'ST2', 'Lina', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'STREET', 'Street', 'ST3', 'Macasaet', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'STREET', 'Street', 'ST4', 'Villapando', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'STREET', 'Street', 'ST5', 'B. Luz', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'STREET', 'Street', 'ST6', 'Medrano', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'STREET', 'Street', 'ST7', 'Maramo', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DISTRICT', 'District', 'D1', 'Purok 1', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DISTRICT', 'District', 'D2', 'Purok 2', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DISTRICT', 'District', 'D3', 'Purok 3', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DISTRICT', 'District', 'D4', 'Purok 4', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DISTRICT', 'District', 'D5', 'Purok 5', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DISTRICT', 'District', 'D6', 'Purok 6', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);
insert into bbop_code(id, category, category_descr, code, code_descr, is_active, created_by, created_date, version) values (next value for bbop_code_seq, 'DISTRICT', 'District', 'D7', 'Purok 7', TRUE, 'SYSTEM', CURRENT_TIMESTAMP, 0);

select * from bbop_code;