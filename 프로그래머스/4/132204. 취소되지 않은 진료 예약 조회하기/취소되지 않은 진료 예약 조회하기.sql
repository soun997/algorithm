select a.apnt_no, p.pt_name, p.pt_no, a.mcdp_cd, d.dr_name, a.apnt_ymd
from appointment as a
left join patient as p
on a.pt_no = p.pt_no
left join doctor d
on a.mddr_id = d.dr_id
where apnt_cncl_yn = 'N'
and date(apnt_ymd) = date('2022-04-13')
and a.mcdp_cd = 'CS'
order by apnt_ymd;