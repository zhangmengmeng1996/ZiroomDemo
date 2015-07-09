create proc ziroom
as
declare @i int 
        set @i = 1
        while @i < 60000000 
        begin
                 insert into work (id,job,username) values('link_'+convert(nvarchar,@i), null,slect username from user order by date); 
                 set @i = @i + 1;
        end
