CREATE DATABASE Player_Minesweeper
GO

USE Player_Minesweeper
GO

CREATE TABLE Player
(
	playerName varchar(16) primary key,
	timeWin int,
	xepHang int
)

insert into Player 
values
('nkd','60',null),
('thv','50',null)

select * from Player

update Player
Set timeWin = 35

where playerName = 'aaaqweq'



DELETE FROM Player WHERE playerName='xinmayday';


SELECT Top 10 playerName, timeWin,
    ROW_NUMBER() OVER (ORDER BY timeWin) AS xepHang
FROM Player

SELECT playerName, ROW_NUMBER() OVER (ORDER BY timeWin) AS xepHang FROM Player

CREATE TRIGGER set_timeWin_default
ON Player
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;
    
    INSERT INTO Player (playerName, timeWin, xepHang)
    SELECT playerName, 9999, xepHang
    FROM inserted;
END;


insert into Player 
values
('nkd1234',null,null)