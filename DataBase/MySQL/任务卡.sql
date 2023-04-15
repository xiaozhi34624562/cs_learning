mysql> select * from salary;
+-------+------------+-------------+-----------+
| empid | basesalary | titlesalary | deduction |
+-------+------------+-------------+-----------+
|     1 |       3000 |        4000 |       100 |
|     2 |       3300 |        1000 |       200 |
|     3 |       3000 |        2000 |       600 |
|     4 |       4000 |        1000 |       500 |
|     5 |       4000 |        1000 |       140 |
|     6 |       4000 |        1000 |       400 |
|     7 |       1000 |        2000 |       100 |
|     8 |       1000 |        2300 |       400 |
|     9 |       1000 |        1500 |       200 |
+-------+------------+-------------+-----------+
9 rows in set (0.00 sec)

mysql> select * from employee;
+-------+--------+------+----------+------------+-------+
| empid | name   | sex  | title    | birthday   | depid |
+-------+--------+------+----------+------------+-------+
|     1 | 张三   | m    | manager  | 1995-10-01 |     1 |
|     2 | 李三   | m    | engineer | 1995-10-05 |     1 |
|     3 | 张四   | m    | engineer | 1998-10-01 |     1 |
|     4 | 张万   | f    | driver   | 1980-10-01 |     2 |
|     5 | 李娃   | m    | engineer | 1990-10-05 |     2 |
|     6 | 张点   | m    | engineer | 1998-10-01 |     2 |
|     7 | 张让   | f    | hr       | 1980-10-01 |     3 |
|     8 | 李法   | m    | hr       | 1998-10-05 |     3 |
|     9 | 张让   | m    | hr       | 1987-10-01 |     3 |
+-------+--------+------+----------+------------+-------+
9 rows in set (0.00 sec)

mysql> select * from department;
+-------+-----------+
| depid | depname   |
+-------+-----------+
|     1 | 财务部    |
|     2 | 运输部    |
|     3 | 人事部    |
+-------+-----------+
3 rows in set (0.00 sec)

#############################################################################
mysql> alter table department add info varchar(20);
Query OK, 0 rows affected (0.05 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> select * from department;
+-------+-----------+------+
| depid | depname   | info |
+-------+-----------+------+
|     1 | 财务部    | NULL |
|     2 | 运输部    | NULL |
|     3 | 人事部    | NULL |
+-------+-----------+------+
3 rows in set (0.00 sec)

mysql> update department set info='发工资' where depid = 1;
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> update department set info='货运' where depid = 2;
Query OK, 1 row affected (0.01 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> update department set info='招聘或者开除' where depid = 3;
Query OK, 1 row affected (0.01 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> select * from department;
+-------+-----------+--------------------+
| depid | depname   | info               |
+-------+-----------+--------------------+
|     1 | 财务部    | 发工资             |
|     2 | 运输部    | 货运               |
|     3 | 人事部    | 招聘或者开除       |
+-------+-----------+--------------------+
3 rows in set (0.00 sec)



############################################################################

mysql> update salary set basesalary = 2000,titlesalary = 700 where empid = (select empid from employee
 where name = '李四');
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> select * from salary;
+-------+------------+-------------+-----------+
| empid | basesalary | titlesalary | deduction |
+-------+------------+-------------+-----------+
|     1 |       3000 |        4000 |       100 |
|     2 |       3300 |        1000 |       200 |
|     3 |       2000 |         700 |       600 |
|     4 |       4000 |        1000 |       500 |
|     5 |       4000 |        1000 |       140 |
|     6 |       4000 |        1000 |       400 |
|     7 |       1000 |        2000 |       100 |
|     8 |       1000 |        2300 |       400 |
|     9 |       1000 |        1500 |       200 |
+-------+------------+-------------+-----------+
9 rows in set (0.00 sec)
#############################################################################

mysql> delete from employee where depid = (select depid from department where depid = '人事部');
Query OK, 0 rows affected (0.00 sec)

mysql> select * from employee;
+-------+--------+------+----------+------------+-------+
| empid | name   | sex  | title    | birthday   | depid |
+-------+--------+------+----------+------------+-------+
|     1 | 张三   | m    | manager  | 1995-10-01 |     1 |
|     2 | 李三   | m    | engineer | 1995-10-05 |     1 |
|     3 | 李四   | m    | engineer | 1998-10-01 |     1 |
|     4 | 张万   | f    | driver   | 1980-10-01 |     2 |
|     5 | 李娃   | m    | engineer | 1990-10-05 |     2 |
|     6 | 张点   | m    | engineer | 1998-10-01 |     2 |
|     7 | 张让   | f    | hr       | 1980-10-01 |     3 |
|     8 | 李法   | m    | hr       | 1998-10-05 |     3 |
|     9 | 张让   | m    | hr       | 1987-10-01 |     3 |
+-------+--------+------+----------+------------+-------+
9 rows in set (0.00 sec)

mysql> delete from employee where depid = (select depid from department where depname = '人事部');
Query OK, 3 rows affected (0.00 sec)

mysql> select * from employee;
+-------+--------+------+----------+------------+-------+
| empid | name   | sex  | title    | birthday   | depid |
+-------+--------+------+----------+------------+-------+
|     1 | 张三   | m    | manager  | 1995-10-01 |     1 |
|     2 | 李三   | m    | engineer | 1995-10-05 |     1 |
|     3 | 李四   | m    | engineer | 1998-10-01 |     1 |
|     4 | 张万   | f    | driver   | 1980-10-01 |     2 |
|     5 | 李娃   | m    | engineer | 1990-10-05 |     2 |
|     6 | 张点   | m    | engineer | 1998-10-01 |     2 |
+-------+--------+------+----------+------------+-------+
6 rows in set (0.00 sec)

#############################################################################
mysql> select empid,basesalary+titlesalary-deduction as receivedsalary,basesalary+titlesalary as total_salary from salary;
+-------+----------------+--------------+
| empid | receivedsalary | total_salary |
+-------+----------------+--------------+
|     1 |           6900 |         7000 |
|     2 |           4100 |         4300 |
|     3 |           2100 |         2700 |
|     4 |           4500 |         5000 |
|     5 |           4860 |         5000 |
|     6 |           4600 |         5000 |
|     7 |           2900 |         3000 |
|     8 |           2900 |         3300 |
|     9 |           2300 |         2500 |
+-------+----------------+--------------+
9 rows in set (0.00 sec)

#############################################################################
mysql> select e.*,e1.age from employee as e join (select empid,name,year(from_days(datediff(now(), birthday))) as age from employee) as e1 on e.empid = e1.empid where e1.name like '  %' and age<40;
+-------+--------+------+----------+------------+-------+------+
| empid | name   | sex  | title    | birthday   | depid | age  |
+-------+--------+------+----------+------------+-------+------+
|     2 | 李三   | m    | engineer | 1995-10-05 |     1 |   25 |
|     3 | 李四   | m    | engineer | 1998-10-01 |     1 |   22 |
|     5 | 李娃   | m    | engineer | 1990-10-05 |     2 |   30 |
+-------+--------+------+----------+------------+-------+------+
3 rows in set (0.00 sec)


#############################################################################
mysql> select s.empid,e2.name,e2.title,d.depname,s.basesalary+s.titlesalary-s.deduction as receivedsalary from salary as s left join (select empid,name,title,depid from employee) as e2 on s.empid = e2.emp
id left join (select depid,depname from department) as d on e2.depid = d.depid;
+-------+--------+----------+-----------+----------------+
| empid | name   | title    | depname   | receivedsalary |
+-------+--------+----------+-----------+----------------+
|     1 | 张三   | manager  | 财务部    |           6900 |
|     2 | 李三   | engineer | 财务部    |           4100 |
|     3 | 李四   | engineer | 财务部    |           2100 |
|     4 | 张万   | driver   | 运输部    |           4500 |
|     5 | 李娃   | engineer | 运输部    |           4860 |
|     6 | 张点   | engineer | 运输部    |           4600 |
|     7 | NULL   | NULL     | NULL      |           2900 |
|     8 | NULL   | NULL     | NULL      |           2900 |
|     9 | NULL   | NULL     | NULL      |           2300 |
+-------+--------+----------+-----------+----------------+
9 rows in set (0.00 sec)


#############################################################################
mysql> select e.name,s.basesalary+s.titlesalary from employee as e join salary as s on e.empid = s.empid;
+--------+----------------------------+
| name   | s.basesalary+s.titlesalary |
+--------+----------------------------+
| 张三   |                       7000 |
| 李三   |                       4300 |
| 李四   |                       2700 |
| 张万   |                       5000 |
| 李娃   |                       5000 |
| 张点   |                       5000 |
+--------+----------------------------+
6 rows in set (0.00 sec)


#############################################################################
mysql> select title,count(*) from employee group by title;
+----------+----------+
| title    | count(*) |
+----------+----------+
| driver   |        1 |
| engineer |        4 |
| manager  |        1 |
+----------+----------+
3 rows in set (0.00 sec)


#############################################################################
mysql> select d.depname,sum(s.basesalary+s.titlesalary+s.deduction) as receivedsalary,avg(s.basesalary+s.titlesalary+s.deduction) as avgsalary from department as d left join employee as e on d.depid = e.depid left join salary as s on e.empid = s.empid group by d.depname;
+-----------+----------------+-----------+
| depname   | receivedsalary | avgsalary |
+-----------+----------------+-----------+
| 人事部    |           NULL |      NULL |
| 财务部    |          14900 | 4966.6667 |
| 运输部    |          16040 | 5346.6667 |
+-----------+----------------+-----------+
3 rows in set (0.00 sec)

#############################################################################
mysql> select e1.name from employee as e1 join salary as s1 on e1.empid = s1.empid where s1.basesalary > (select avg(basesalary) from salary where empid in (select empid from employee where depid = (select depid from department where depname = '财务部')));
+--------+
| name   |
+--------+
| 张三   |
| 李三   |
| 张万   |
| 李娃   |
| 张点   |
+--------+
5 rows in set (0.00 sec)
