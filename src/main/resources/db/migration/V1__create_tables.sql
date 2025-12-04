-- V1__create_tables.sql
-- Create tables for Medicine and Sale

-- Medicine table
CREATE TABLE medicine (
  id VARCHAR2(36) PRIMARY KEY,
  name VARCHAR2(255) NOT NULL,
  factory_laboratory VARCHAR2(255),
  date_manufactured DATE,
  expiration_date DATE,
  quantity_in_stock NUMBER(10,0) DEFAULT 0,
  unit_price NUMBER(15,2) DEFAULT 0
);

-- Unique index on lower(name) to enforce case-insensitive uniqueness
CREATE UNIQUE INDEX ux_medicine_name ON medicine (LOWER(name));

-- Constraint: expiration date must be after manufactured date when both present
ALTER TABLE medicine ADD CONSTRAINT chk_expiration_after_manufactured CHECK (
  expiration_date IS NULL OR date_manufactured IS NULL OR expiration_date > date_manufactured
);

-- Sale table
CREATE TABLE sale (
  id VARCHAR2(36) PRIMARY KEY,
  date_time TIMESTAMP,
  medicine_id VARCHAR2(36),
  quantity NUMBER(10,0),
  unit_price NUMBER(15,2),
  total_price NUMBER(15,2)
);

-- Foreign key to medicine
ALTER TABLE sale ADD CONSTRAINT fk_sale_medicine FOREIGN KEY (medicine_id) REFERENCES medicine(id);

-- Index to speed up queries by medicine
CREATE INDEX idx_sale_medicine_id ON sale (medicine_id);
