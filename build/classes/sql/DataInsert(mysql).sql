----------------���������Ϣ�����ݼ�¼----------------
USE DB_TeachingMS
/* ϵ */
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('02','���繤��ϵ');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('03','���ӹ���ϵ');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('05','���Ĺ���ϵ');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('06','����ϵ');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('07','�������ϵ');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('08','�����ϵ');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('09','����ϵ');
INSERT INTO TB_Dept (DeptID,DeptName) VALUES('10','������');

/* �༶ */
INSERT INTO TB_Class (ClassID,ClassName,DeptID)
VALUES('040801','04����(1)��','08');
INSERT INTO TB_Class (ClassID,ClassName,DeptID) 
VALUES('040802','04����(2)��','08');
INSERT INTO TB_Class (ClassID,ClassName,DeptID) 
VALUES('050801','05���(1)��','08');
INSERT INTO TB_Class (ClassID,ClassName,DeptID) 
VALUES('040201','04����(1)��','02');
INSERT INTO TB_Class (ClassID,ClassName,DeptID) 
VALUES('050302','05����(2)��','03');

/* ����Ա */
INSERT INTO TB_Admin (AdminID,APassword,tel) 
VALUES('00001','123456','17816869693');

----------------���������Ϣ�����ݼ�¼----------------

/* ��ʦ */
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro ) 
VALUES('T08001','����','08','F','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T08002','���շ�','08','M','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T08003','����ͼ','08','M','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T08004','������','08','F','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T08005','����','08','M','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T02001','�̾�','02','F','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T10002','����һ','10','F','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T10005','��Զ','10','M','17816869692','xxxxxxxx');
INSERT INTO TB_Teacher (TeacherID,TeacherName,DeptID,Sex,tel,Intro) 
VALUES('T07002','����','07','M','17816869692','xxxxxxxx');

/* ѧ�� */
---04����(1)��---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,SPassword,Grade,tel,Intro)
VALUES('04080101','������','08','040801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080102','��ٻ','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080103','����','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro)
VALUES('04080104','�����','08','040801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080105','֣־','08','040801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080106','������','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080107','����','08','040801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080108','������','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080109','������','08','040801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080110','˾���','08','040801','M','3.0','17816869693','xxxxxx');
---04����(2)��---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080201','�Ž���','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080202','������','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro)
VALUES('04080203','ʯ����','08','040802','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080204','�½�ΰ','08','040802','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080205','Ԭ�б�','08','040802','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro)
VALUES('04080206','��ɯɯ','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080207','���л�','08','040802','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro)
VALUES('04080208','��ӱ','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080209','������','08','040802','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04080210','Τ��','08','040802','M','3.0','17816869693','xxxxxx');
---05���(1)��---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080101','����','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080102','������','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080103','��ԭ','08','050801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080104','ë����','08','050801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080105','����','08','050801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080106','���','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080107','��С��','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080108','����','08','050801','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080109','������','08','050801','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05080110','ʯ��','08','050801','M','3.0','17816869693','xxxxxx');
---04����(1)��---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020101','������','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020102','�����','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020103','����ϼ','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020104','������','02','040201','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020105','���ɲ�','02','040201','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020106','����ʢ','02','040201','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020107','������','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020108','�ܱ���','02','040201','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020109','�����','02','040201','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('04020110','������','02','040201','M','3.0','17816869693','xxxxxx');
---05����(2)��---
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030201','���ݷ�','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030202','�����','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030203','������','03','050302','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030204','����','03','050302','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030205','��ѧ��','03','050302','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030206','����ϼ','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030207','�ֲ���','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030208','���Ļ�','03','050302','M','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030209','��ΰ','03','050302','F','3.0','17816869693','xxxxxx');
INSERT INTO TB_Student (StuID,StuName,DeptID,ClassID,Sex,Grade,tel,Intro) 
VALUES('05030210','��ϼ','03','050302','M','3.0','17816869693','xxxxxx');