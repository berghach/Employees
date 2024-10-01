create table if not exists department(
	id serial,
	name varchar(30),
	constraint pk_department primary key(id)
);
create table if not exists job(
	id serial,
	name varchar(30),
	department_id int,
	constraint pk_job primary key(id),
	constraint fk_job_department foreign key(department_id)
		references department(id)
);

create table if not exists employee(
	id serial,
	name varchar(30),
	email varchar(30),
	phone varchar(10),
	department_id int,
	job_id int,
	constraint pk_employee primary key(id),
	constraint fk_employee_department foreign key(department_id)
		references department(id),
	constraint fk_job foreign key(job_id)
		references job(id)
);

INSERT INTO department (name) VALUES
('Human Resources'),
('Finance'),
('Engineering'),
('Sales'),
('Marketing');

INSERT INTO job (name, department_id) VALUES
('HR Manager', 1),          
('HR Specialist', 1),        
('Accountant', 2),           
('Software Engineer', 3),    
('Sales Representative', 4),
('Marketing Manager', 5);

INSERT INTO employee (name, email, phone, department_id, job_id) VALUES
('Alice Smith', 'alice.smith@example.com', '1234567890', 1, 1),
('Bob Johnson', 'bob.johnson@example.com', '2345678901', 1, 2),
('Charlie Brown', 'charlie.brown@example.com', '3456789012', 2, 3),
('David Wilson', 'david.wilson@example.com', '4567890123', 3, 4),
('Eva Green', 'eva.green@example.com', '5678901234', 4, 5),
('Grace Black', 'grace.black@example.com', '6789012345', 5, 6);
