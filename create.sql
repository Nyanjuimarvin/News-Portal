CREATE DATABASE organization_news;
\c organization_news;
CREATE TABLE members(id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR,roles TEXT[], rolesstring VARCHAR, departmentid INTEGER);
CREATE TABLE admins(id SERIAL PRIMARY KEY,name VARCHAR);
CREATE TABLE news(id SERIAL PRIMARY KEY, information VARCHAR, category VARCHAR,datecreated BIGINT,readableDate VARCHAR, type VARCHAR);
CREATE TABLE departments(id SERIAL PRIMARY KEY, name  VARCHAR, description VARCHAR);
CREATE TABLE department_news(id SERIAL PRIMARY KEY, departmentid INTEGER, newsid INTEGER);
CREATE DATABASE organization_news_test WITH TEMPLATE organization_news;