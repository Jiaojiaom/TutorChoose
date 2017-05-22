/* 创建数据库 */
create database DB_TeachingMS;
/* 数据库名 */
USE DB_TeachingMS;

/* 导入 */

/* 系 */
CREATE TABLE TB_Dept                  
( DeptID varchar(10) PRIMARY KEY,                                             
  DeptName varchar(20) NOT NULL
);
/* 班级 */
CREATE TABLE TB_Class                  
( ClassID VARCHAR(10) PRIMARY KEY,                                     
  ClassName VARCHAR(20) NOT NULL,  
  DeptID VARCHAR(10) NOT NULL REFERENCES TB_Dept(DeptID)
);

/* 管理员 */
CREATE TABLE TB_Admin             
( AdminID VARCHAR(16) PRIMARY KEY,
  AdminName VARCHAR(30),                                                          
  APassword VARCHAR(16) NOT NULL DEFAULT "123456",
  tel VARCHAR(13),
  PrivilegeModel VARCHAR(3) NOT NULL DEFAULT "off",
  AuthorityModel VARCHAR(3) NOT NULL DEFAULT "off",
  LimitModel VARCHAR(3) NOT NULL DEFAULT "off"
); 

/* 老师 */
CREATE TABLE TB_Teacher                  
( TeacherID VARCHAR(16) PRIMARY KEY,                                             
  TeacherName VARCHAR(30) NOT NULL,   
  TPassword VARCHAR(16) DEFAULT '123456',  
  DeptID VARCHAR(2) NOT NULL REFERENCES TB_Dept(DeptID),
  Sex VARCHAR(1) NOT NULL DEFAULT 'M',  
  Title VARCHAR(10) NOT NULL  DEFAULT '副教授',
  studentCount INTEGER NOT NULL DEFAULT 5 , 
  Privilege INTEGER NOT NULL DEFAULT 3  ,
  tel VARCHAR(13),
  Intro TEXT
);
/* 限选人数*/
/* 特权 0-表示开启反选模式，
 * 因为特权模式和选择规则不冲突，
 * 所以这个字段可以用1-表示按照先到先得，2-表示按照绩点
 */
/* 学生 */
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
/* 未选择0 | 待定1 | 淘汰2 | 成功3*/

/* 选择导师的过程中产生 */
/* 学生选择导师表 */
CREATE TABLE TB_SelectTeacher               
( StuID VARCHAR(16) NOT NULL REFERENCES TB_Student(StuID),
  TeacherID VARCHAR(16) NOT NULL REFERENCES TB_Teacher(TeacherID),
  SelectDate DATETIME DEFAULT NOW(),
  Grade Double NOT NULL, /*绩点*/
  choosedState INTEGER DEFAULT 0, /* 待定0 | 淘汰1 | 成功2*/
  CONSTRAINT PK_StuID_TeacherID PRIMARY KEY (StuID, TeacherID, SelectDate)
);