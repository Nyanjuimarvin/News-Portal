CREATE DATABASE organization_news;
\c organization_news;
CREATE TABLE employees(id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR,roles VARCHAR, departmentid INTEGER);
CREATE TABLE news(id SERIAL PRIMARY KEY, information VARCHAR, category VARCHAR, type VARCHAR);
CREATE TABLE departments(id SERIAL PRIMARY KEY, name  VARCHAR, description VARCHAR , employeeid INTEGER, size INTEGER);
CREATE TABLE department_news(id SERIAL PRIMARY KEY, departmentid INTEGER, newsid INTEGER);
CREATE DATABASE organization_news_test WITH TEMPLATE organization_news;