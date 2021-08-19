# 开发

```sql
-- 当年拆包时，有的信件的时间是错误的，时间中的“日” 为0
select id,create_date , 
str_to_date(date_format(create_date,'%m.01.%Y %h:%i:%s'),'%m.%d.%Y %h:%i:%s')
from mail 
where date_format(create_date,'%d')='00'
limit 10;

-- 修复时间
update mail
set create_date = str_to_date(date_format(create_date,'%m.01.%Y %h:%i:%s'),'%m.%d.%Y %h:%i:%s')
where date_format(create_date,'%d')='00'
```

```sh
git push -v --progress "origin" dev:dev
```