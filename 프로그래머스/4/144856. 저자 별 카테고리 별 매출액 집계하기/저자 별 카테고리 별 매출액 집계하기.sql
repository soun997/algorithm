select 
    a.author_id,
    a.author_name,
    b.category,
    sum(bs.sales * b.price) as total_sales
from book_sales as bs
left join book b on b.book_id = bs.book_id
left join author a on a.author_id = b.author_id
where year(sales_date) = 2022 and month(sales_date) = 1
group by a.author_id, b.category
order by a.author_id, b.category desc;