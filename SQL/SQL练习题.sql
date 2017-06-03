/*
** 练习SQL的练习题（使用数据库为：MySQL）
*/
-- 20道查询练习题
-- 1.分别查询学生表和学生课表中的全部数据：
-- SELECT s.s_id '学生ID', s.s_name '学生姓名', s.s_sex '性别', s.s_age '年龄', d.d_name '学院'
-- FROM student AS s 
-- JOIN department AS d ON s.s_department = d.d_id;
-- 
-- SELECT c_id '课程ID', c_name '课程名称', c_hours '学时', c_credit '学分' 
-- FROM course;


-- 2. 查询成绩在70到80分之间的学生的学号、课程号和成绩：
-- select s.s_name '姓名', c.c_name '课程', cv.grade '成绩' FROM curricula_variable AS cv
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- WHERE cv.grade BETWEEN 70 AND 80

-- 3. 查询没门课程成绩最高的分数：
-- SELECT c.c_name '课程名', MAX(grade) '最高分'
-- FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- GROUP BY cv.cv_fk_cid;

-- 4. 查询学生都选修了哪些课程,要求列出课程号：
-- SELECT c_name '已选课程', c_id '课程编号' 
-- FROM course 
-- WHERE c_id IN (SELECT DISTINCT cv_fk_cid FROM curricula_variable);

-- 4升级版. 查询学生都选修了哪些课程,要求列出课程号，并显示选课人数：
-- SELECT c.c_name '已选课程', c.c_id '课程编号', COUNT(cv.cv_fk_sid) '选课人数' FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- GROUP BY cv.cv_fk_cid;

-- 5. 查询修了每门课程的所有学生的平均成绩、最高成绩和最低成绩
-- SELECT c.c_name'课程名称', AVG(cv.grade)'平均成绩', MAX(cv.grade)'最高成绩', MIN(cv.grade)'最低成绩'
-- FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- GROUP BY cv_fk_cid;

-- 6. 统计每个系的学生人数：
-- SELECT DISTINCT d.d_name'学院', COUNT(s.s_department) '学生人数' FROM student AS s
-- JOIN department AS d ON d.d_id = s.s_department
-- GROUP BY s.s_department
-- ORDER BY d.d_name

-- 7. 统计每门课程的修课人数和考试最高分：
-- SELECT c.c_name'课程名称', COUNT(*)'修课人数', MAX(cv.grade)'最高分'
-- FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- GROUP BY cv.cv_fk_cid;

-- 使用where查询
-- SELECT c.c_name'课程名称', COUNT(*)'修课人数', MAX(cv.grade)'最高分' 
-- FROM course AS c, curricula_variable AS cv
-- WHERE c.c_id = cv.cv_fk_cid
-- GROUP BY cv.cv_fk_cid;

-- 8. 统计每个学生的选课门数,并按选课门数的递增顺序显示结果:
-- SELECT s.s_name'学生姓名', COUNT(cv.cv_fk_cid)'选课数量'
-- FROM curricula_variable AS cv
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- GROUP BY cv.cv_fk_sid, s.s_name
-- ORDER BY COUNT(cv.cv_fk_cid) ASC;

-- 9. 统计参加选修课的学生总数和考试的平均成绩。
-- SELECT COUNT(DISTINCT cv_fk_sid)'参加选课的人数', AVG(grade)'平均成绩'
-- FROM curricula_variable;

-- 10. 查询没有参加选课的学生
-- SELECT s.s_id '学生ID', s.s_name '学生姓名', s.s_sex '性别', s.s_age '年龄', d.d_name '学院'
-- FROM student AS s
-- JOIN department AS d ON d.d_id = s.s_department
-- WHERE s_id NOT IN (SELECT DISTINCT cv_fk_sid FROM curricula_variable);

-- 11. 查询没有被选的课程
-- SELECT c_id '课程ID', c_name '课程名称', c_hours '学时', c_credit '学分' 
-- FROM course
-- WHERE c_id NOT IN (SELECT DISTINCT cv_fk_cid FROM curricula_variable);

-- 12. 查询成绩为空的学生名称和课程
-- SELECT s.s_name'学生姓名', c.c_name'课程名称'
-- FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- WHERE cv.grade IS NULL;

-- 13. 查询选课门数超过2门的学生的平均成绩和选课门数：
-- SELECT s.s_name'学生姓名', AVG(grade)'平均成绩', COUNT(cv.cv_fk_cid)'选课门数' FROM curricula_variable AS cv
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- GROUP BY cv.cv_fk_sid
-- HAVING COUNT(cv.cv_fk_cid) > 2;

-- 14. 列出总成绩超过300分的学生,要求列出学号、总成绩:
-- SELECT s.s_id'学生编号', s.s_name'学生姓名', SUM(cv.grade)'总成绩'
-- FROM curricula_variable AS cv
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- GROUP BY cv.cv_fk_sid
-- HAVING SUM(cv.grade) > 300;

-- 15. 查询选修了11号课程的学生的姓名和所在学院：
-- SELECT c.c_name'课程名称', s.s_name'选课学生', d.d_name'所在学院' FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- JOIN department AS d ON d.d_id = s.s_department
-- WHERE cv.cv_fk_cid = 11;

-- 16. 查询成绩80分以上的学生的姓名、课程和成绩,并按成绩的降序排列结果:
-- SELECT c.c_name'课程名称', s.s_name'选课学生', cv.grade'成绩' 
-- FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- WHERE cv.grade > 80
-- ORDER BY cv.grade DESC;

-- 17.查询信息管理学院男生修了"计算机组成与系统结构"的学生的姓名、性别、成绩：
-- SELECT c.c_name'课程名', s_name'选课学生', s_sex'性别', cv.grade'成绩', d.d_name'学院'
-- FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid AND c.c_name = '计算机组成与系统结构'
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid AND s.s_sex = '男'
-- JOIN department AS d ON d.d_id = s.s_department AND d.d_name = '信息管理学院'

-- 18. 查询哪些学生的年龄相同,要求列出年龄相同的学生的姓名和年龄：
-- （不懂有什么用，直接按照年龄排序不就好了么）
-- SELECT s1.s_name'学生姓名', s1.s_age'年龄', s2.s_name'年龄相同的学生', s2.s_age'相同年龄' 
-- FROM student AS s1
-- INNER JOIN student AS s2 ON s1.s_age IN (SELECT s_age FROM student WHERE s1.s_age = s2.s_age AND s1.s_name != s2.s_name)
-- GROUP BY s1.s_name, s1.s_age
-- ORDER BY s1.s_age;

-- 19. 分别查询信息管理学院和计算机学院的学生的姓名、性别、修课名称、修课成绩,
-- 并要求将这两个查询结果合并成一个结果集,
-- 并以系名、姓名、性别、修课名称、修课成绩的顺序显示各列。
-- SELECT d.d_name'学院名称', s.s_name'学生名称', s.s_sex'性别', c.c_name'课程名称', cv.grade'成绩' 
-- FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- JOIN department AS d ON d.d_id = s.s_department AND d.d_name = '信息管理学院'
-- UNION
-- SELECT d.d_name'学院名称', s.s_name'学生名称', s.s_sex'性别', c.c_name'课程名称', cv.grade'成绩' 
-- FROM curricula_variable AS cv
-- JOIN course AS c ON c.c_id = cv.cv_fk_cid
-- JOIN student AS s ON s.s_id = cv.cv_fk_sid
-- JOIN department AS d ON d.d_id = s.s_department AND d.d_name = '计算机学院';

-- 20. 用子查询实现如下查询:
-- 1) 查询选修了11号课程的学生的姓名和所在学院：
-- SELECT s.s_name'学生姓名', d.d_name'学院' 
-- FROM  student AS s, department AS d
-- WHERE s.s_department = d.d_id
-- 	AND s.s_id IN (SELECT cv_fk_sid from curricula_variable WHERE cv_fk_cid = 11);
-- 
-- 2) 查询信息管理学院成绩80分以上的学生的学号、姓名
-- SELECT s_id'学生编号', s_name'学生姓名'
-- FROM student
-- WHERE s_department IN (SELECT d_id FROM department WHERE d_name = "信息管理学院")
-- 	AND s_id IN (SELECT cv_fk_sid FROM curricula_variable WHERE grade > 80);

-- 3) 查询计算机学院学生所选的课程名：
SELECT c_id'课程编号', c_name'课程名称'
FROM course
WHERE c_id IN 
	(SELECT cv_fk_cid FROM curricula_variable 
		WHERE cv_fk_sid IN
			(SELECT s_id FROM student 
				WHERE s_department = (SELECT d_id FROM department 
					WHERE d_name = '计算机学院')
			)
	);