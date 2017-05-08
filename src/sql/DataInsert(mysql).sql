----------------插入基础信息表数据记录----------------
USE DB_TeachingMS
/* 系 */
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('02','机电工程系');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('03','电子工程系');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('05','化纺工程系');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('06','外语系');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('07','艺术设计系');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('08','计算机系');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('09','管理系');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('10','基础部');

/* 班级 */
INSERT INTO TB_Class (ClassID,ClassName,DeptID)
VALUES('040801','04网络(1)班','08');
INSERT INTO TB_Class (ClassID,ClassName,DeptID) 
VALUES('040802','04网络(2)班','08');
INSERT INTO TB_Class (ClassID,ClassName,DeptID) 
VALUES('050801','05软件(1)班','08');
INSERT INTO TB_Class (ClassID,ClassName,DeptID) 
VALUES('040201','04机电(1)班','02');
INSERT INTO TB_Class (ClassID,ClassName,DeptID) 
VALUES('050302','05电子(2)班','03');

/* 管理员 */
INSERT INTO TB_Admin (AdminID,APassword,tel) 
VALUES('00001','123456','17816869693');

----------------插入对象信息表数据记录----------------

/* 老师 */
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro ) 
VALUES('T08001','陈玲','08','F','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T08002','李琳分','08','M','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T08003','龙永图','08','M','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T08004','黄三清','08','F','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T08005','韩汉','08','M','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T02001','程靖','02','F','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T10002','沈天一','10','F','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T10005','曾远','10','M','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T07002','沈丽','07','M','17816869692','xxxxxxxx');

/* 学生 */
---04网络(1)班---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,SPassword,Grade,tel,Intro)
VALUES('04080101','任正非','08','040801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080102','王倩','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080103','戴丽','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro)
VALUES('04080104','孙军团','08','040801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080105','郑志','08','040801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080106','龚玲玲','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080107','李铁','08','040801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080108','戴安娜','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080109','陈淋淋','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080110','司马光','08','040801','M','3.0','17816869693','xxxxxx');
---04网络(2)班---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080201','张金玲','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080202','王婷婷','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro)
VALUES('04080203','石江安','08','040802','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080204','陈建伟','08','040802','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080205','袁中标','08','040802','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro)
VALUES('04080206','崔莎莎','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080207','丁承华','08','040802','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro)
VALUES('04080208','刘颖','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080209','刘玉芹','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080210','韦涛','08','040802','M','3.0','17816869693','xxxxxx');
---05软件(1)班---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080101','李娟娟','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080102','刘晓燕','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080103','高原','08','050801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080104','毛振兴','08','050801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080105','陈立','08','050801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080106','朱凤','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080107','王小丽','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080108','曹勇','08','050801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080109','赵启悦','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080110','石慧','08','050801','M','3.0','17816869693','xxxxxx');
---04机电(1)班---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020101','周灵灵','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020102','余红燕','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020103','左秋霞','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020104','汪德荣','02','040201','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020105','刘成波','02','040201','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020106','郭昌盛','02','040201','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020107','陈玲玲','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020108','周炳才','02','040201','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020109','李克寅','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020110','刘争艳','02','040201','M','3.0','17816869693','xxxxxx');
---05电子(2)班---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030201','刘惠凤','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030202','孙嘉珍','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030203','单瑞中','03','050302','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030204','陈颂','03','050302','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030205','包学诚','03','050302','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030206','胡春霞','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030207','贾彩丽','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030208','高文欢','03','050302','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030209','沈伟','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030210','马霞','03','050302','M','3.0','17816869693','xxxxxx');