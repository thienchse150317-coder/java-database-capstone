# Database Schema Design

This schema is for a clinic management system with three main roles: Doctor, Patient, and Admin.

---

## Tables

### 1. Users
- **user_id** (INT, PK, AUTO_INCREMENT)  
- **username** (VARCHAR, UNIQUE)  
- **password** (VARCHAR)  
- **role** (ENUM: 'doctor', 'patient', 'admin')  
- **email** (VARCHAR)  

---

### 2. Patients
- **patient_id** (INT, PK, AUTO_INCREMENT)  
- **user_id** (INT, FK → Users.user_id)  
- **full_name** (VARCHAR)  
- **dob** (DATE)  
- **gender** (VARCHAR)  
- **phone** (VARCHAR)  

---

### 3. Doctors
- **doctor_id** (INT, PK, AUTO_INCREMENT)  
- **user_id** (INT, FK → Users.user_id)  
- **full_name** (VARCHAR)  
- **specialty** (VARCHAR)  
- **phone** (VARCHAR)  

---

### 4. Appointments
- **appointment_id** (INT, PK, AUTO_INCREMENT)  
- **patient_id** (INT, FK → Patients.patient_id)  
- **doctor_id** (INT, FK → Doctors.doctor_id)  
- **appointment_date** (DATETIME)  
- **status** (ENUM: 'scheduled', 'completed', 'cancelled')  

---

### 5. Medical_Records
- **record_id** (INT, PK, AUTO_INCREMENT)  
- **appointment_id** (INT, FK → Appointments.appointment_id)  
- **diagnosis** (TEXT)  
- **prescription** (TEXT)  
- **created_at** (TIMESTAMP DEFAULT CURRENT_TIMESTAMP)  

---

## Relationships
- One **User** → can be Doctor, Patient, or Admin.  
- A **Patient** can have many **Appointments**.  
- A **Doctor** can have many **Appointments**.  
- Each **Appointment** → links to one **Medical Record**.  
