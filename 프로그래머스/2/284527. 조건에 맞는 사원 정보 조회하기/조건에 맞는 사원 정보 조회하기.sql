select sum(g.score) as score, e.emp_no, e.emp_name, e.position, e.email
from hr_employees as e
left join hr_grade as g
on e.emp_no = g.emp_no
where g.year = 2022
group by e.emp_no
order by sum(g.score) desc
limit 1;