EXEC GetBoard

-- EXEC GetArticle 1

-- EXEC AddUser 'young', '김영랑','young@naver.com', '모란꽃'

SELECT dbo.GetMaxUserNo()

-- EXEC AddUser 'somonth', '김소월','somonth@daum.net', '소월'

SELECT * FROM Users;

DECLARE @ArticleNo	int = 2
DECLARE @Title	varchar(300)
DECLARE @Contents varchar(4000)
DECLARE @WriterNickName varchar(15)
DECLARE @WriteDate datetime
EXEC GetArticle @ArticleNo, @Title OUTPUT, @Contents OUTPUT, @WriterNickName OUTPUT, @WriteDate OUTPUT
SELECT @ArticleNo, @Title, @Contents, @WriterNickName, @WriteDate