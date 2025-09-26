select 
    car_id,
    if (
        max(
            case 
                when start_date <= date('2022-10-16') and end_date >= date('2022-10-16')
                then 1 # 대여중
                else 0 # 대여 가능
            end
        ) = 1, # 기록 중 하나라도 대여중이라면
        '대여중',
        '대여 가능'
    ) as availability
from car_rental_company_rental_history
group by car_id
order by car_id desc;