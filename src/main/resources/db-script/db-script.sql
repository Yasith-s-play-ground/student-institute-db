CREATE TABLE `user`
(
    username VARCHAR(100) PRIMARY KEY,
    name     VARCHAR(200) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE course
(
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE student
(
    nic     VARCHAR(11) PRIMARY KEY,
    name    VARCHAR(200) NOT NULL,
    address VARCHAR(500) NOT NULL
);

CREATE TABLE contact
(
    student_id VARCHAR(11) NOT NULL,
    contact    VARCHAR(13) PRIMARY KEY,
    CONSTRAINT fk_student_contact FOREIGN KEY (student_id) REFERENCES student (nic)
);

CREATE TABLE module
(
    code    VARCHAR(10) PRIMARY KEY,
    name    VARCHAR(100)                  NOT NULL,
    credits INT                           NOT NULL,
    type    ENUM ('Mandatory','Optional') NOT NULL
);

CREATE TABLE course_module
(
    course_code VARCHAR(10) NOT NULL,
    module_code VARCHAR(10) NOT NULL,
    CONSTRAINT pk_course_module PRIMARY KEY (course_code, module_code),
    CONSTRAINT fk_course FOREIGN KEY (course_code) REFERENCES course (code),
    CONSTRAINT fk_module FOREIGN KEY (module_code) REFERENCES module (code)
);

CREATE TABLE batch
(
    batch_id    VARCHAR(10) PRIMARY KEY,
    course_code VARCHAR(10)   NOT NULL,
    duration    VARCHAR(50)   NOT NULL,
    fee         DECIMAL(9, 2) NOT NULL,
    CONSTRAINT fk_batch_course FOREIGN KEY (course_code) REFERENCES course (code)
);

CREATE TABLE student_registration
(
    student_nic VARCHAR(11)  NOT NULL,
    batch_id    VARCHAR(10)  NOT NULL,
    username    VARCHAR(100) NOT NULL,
    date        DATE         NOT NULL,
    CONSTRAINT pk_student_registration PRIMARY KEY (student_nic, batch_id, username),
    CONSTRAINT fk_register_student FOREIGN KEY (student_nic) REFERENCES student (nic),
    CONSTRAINT fk_register_batch FOREIGN KEY (batch_id) REFERENCES batch (batch_id),
    CONSTRAINT fk_register_user FOREIGN KEY (username) REFERENCES `user` (username)
);

CREATE TABLE student_batch_module
(
    student_id  VARCHAR(11) NOT NULL,
    batch_id    VARCHAR(10) NOT NULL,
    module_code VARCHAR(10) NOT NULL,
    CONSTRAINT pk_student_batch_module PRIMARY KEY (student_id, batch_id, module_code),
    CONSTRAINT fk_take_student FOREIGN KEY (student_id) REFERENCES student (nic),
    CONSTRAINT fk_take_batch FOREIGN KEY (batch_id) REFERENCES batch (batch_id),
    CONSTRAINT fk_take_module FOREIGN KEY (module_code) REFERENCES module (code)
);
