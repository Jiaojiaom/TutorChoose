/* �������ݿ� */
create database DB_TeachingMS;
/* ���ݿ��� */
USE DB_TeachingMS;

/* ���� */

/* ϵ */
CREATE TABLE TB_Dept                  
( DeptID varchar(10) PRIMARY KEY,                                             
  DeptName varchar(20) NOT NULL
);
/* �༶ */
CREATE TABLE TB_Class                  
( ClassID VARCHAR(10) PRIMARY KEY,                                     
  ClassName VARCHAR(20) NOT NULL,  
  DeptID VARCHAR(10) NOT NULL REFERENCES TB_Dept(DeptID)
);

/* ����Ա */
CREATE TABLE TB_Admin             
( AdminID VARCHAR(16) PRIMARY KEY,
  AdminName VARCHAR(30),                                                          
  APassword VARCHAR(16) NOT NULL DEFAULT "123456",
  tel VARCHAR(13),
  PrivilegeModel VARCHAR(3) NOT NULL DEFAULT "off",
  AuthorityModel VARCHAR(3) NOT NULL DEFAULT "off",
  LimitModel VARCHAR(3) NOT NULL DEFAULT "off"
); 

/* ��ʦ */
CREATE TABLE TB_Teacher                  
( TeacherID VARCHAR(16) PRIMARY KEY,                                             
  TeacherName VARCHAR(30) NOT NULL,   
  TPassword VARCHAR(16) DEFAULT '123456',  
  DeptID VARCHAR(2) NOT NULL REFERENCES TB_Dept(DeptID),
  Sex VARCHAR(1) NOT NULL DEFAULT 'M',  
  Title VARCHAR(10) NOT NULL  DEFAULT '������',
  studentCount INTEGER NOT NULL DEFAULT 5 , 
  Privilege INTEGER NOT NULL DEFAULT 3  ,
  tel VARCHAR(13),
  Intro TEXT
);
/* ��ѡ����*/
/* ��Ȩ 0-��ʾ������ѡģʽ��
 * ��Ϊ��Ȩģʽ��ѡ����򲻳�ͻ��
 * ��������ֶο�����1-��ʾ�����ȵ��ȵã�2-��ʾ���ռ���
 */
/* ѧ�� */
CREATE TABLE TB_Student                  
( StuID VARCHAR(16) PRIMARY KEY,                                             
  StuName VARCHAR(30) NOT NULL,                         
  DeptID VARCHAR(10) NOT NULL REFERENCES TB_Dept(DeptID),
  ClassID VARCHAR(6) NOT NULL REFERENCES TB_Class(ClassID),
  Sex VARCHAR(1) DEFAULT 'M',
  SPassword VARCHAR(16) DEFAULT '123456',
  Grade Double NOT NULL,
  tel VARCHAR(13),
  Intro TEXT,
  TeacherID VARCHAR(16) DEFAULT null,
  choosedState INTEGER DEFAULT 0, 
  SelectDate DATETIME DEFAULT NOW()
);     
/* δѡ��0 | ����1 | ��̭2 | �ɹ�3*/

/* ѡ��ʦ�Ĺ����в��� */
/* ѧ��ѡ��ʦ�� */
CREATE TABLE TB_SelectTeacher               
( StuID VARCHAR(16) NOT NULL REFERENCES TB_Student(StuID),
  TeacherID VARCHAR(16) NOT NULL REFERENCES TB_Teacher(TeacherID),
  SelectDate DATETIME DEFAULT NOW(),
  Grade Double NOT NULL, /*����*/
  choosedState INTEGER DEFAULT 0, /* ����0 | ��̭1 | �ɹ�2*/
  CONSTRAINT PK_StuID_TeacherID PRIMARY KEY (StuID, TeacherID, SelectDate)
);