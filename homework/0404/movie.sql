-- 복원한 DatamotionMovieDatabase에서, 아래 질의들을 SQL Query 식으로 작성후 Query를 제출해주세요
-- 01. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.
SELECT ReleaseYear, Title, RunningTime, Plot
FROM Movie
WHERE KoreanTitle = '퍼스트 맨';

-- 02. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
SELECT KoreanTitle, Title
FROM Movie
WHERE ReleaseYear = 2003;

-- 03. 영화 '글래디에이터'의 작곡가를 고르세요
SELECT p.Name, p.KoreanName, r.RoleKorName
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Role r ON a.RoleID = r.RoleID
JOIN Movie m ON a.MovieID = m.MovieID
WHERE m.Title = 'Gladiator' AND r.RoleName = 'Composer';

-- 04. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
SELECT COUNT(*)
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN Role r ON a.RoleID = r.RoleID
WHERE m.KoreanTitle = '매트릭스' AND r.RoleKorName = '감독';

-- 05. 감독이 2명 이상인 영화를 출력하세요
SELECT m.Title, m.KoreanTitle, COUNT(*) as DirectorCount
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN Role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독'
GROUP BY m.Title, m.KoreanTitle
HAVING COUNT(*) > 1;

-- 06. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
SELECT DISTINCT m.Title
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN Awardinvolve ai ON a.AppearID = ai.AppearID
JOIN AwardYear ay ON ai.AwardYearID = ay.AwardYearID
JOIN Award aw ON ay.AwardID = aw.AwardID
JOIN Winning w ON ai.WinningID = w.WinningID
WHERE p.KoreanName = '한스 짐머' AND aw.AwardEnglishTitle = 'Academy Award' AND w.WinOrNot = 'Winner';

-- 07. 감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요
SELECT DISTINCT m.Title
FROM Movie m
JOIN Appear a1 ON m.MovieID = a1.MovieID
JOIN Person p1 ON a1.PersonID = p1.PersonID
JOIN Role r1 ON a1.RoleID = r1.RoleID
JOIN Appear a2 ON m.MovieID = a2.MovieID
JOIN Person p2 ON a2.PersonID = p2.PersonID
JOIN Role r2 ON a2.RoleID = r2.RoleID
WHERE p1.KoreanName = '제임스 카메론'
AND r1.RoleKorName = '감독'
AND p2.KoreanName = '아놀드 슈워제네거'
AND r2.RoleKorName = '배우';

-- 08. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
SELECT m.Title
FROM Movie m
JOIN Appear a ON m.MovieID = a.MovieID
JOIN Person p ON a.PersonID = p.PersonID
WHERE p.KoreanName = '레오나르도 디카프리오' AND m.RunningTime >= 100;

-- 09. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
SELECT Title, BoxOfficeWWGross
FROM Movie
WHERE GradeInKoreaID = 4
ORDER BY BoxOfficeWWGross DESC
LIMIT 1;

-- 10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오
SELECT AVG(BoxOfficeWWGross) as AverageGross
FROM Movie
WHERE ReleaseYear < 1999;

-- 11. 가장 많은 제작비가 투입된 영화를 고르시오.
SELECT Title, Budget
FROM Movie
ORDER BY Budget DESC
LIMIT 1;

-- 12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?
SELECT p.Name, SUM(m.Budget) AS TotalBudget
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN Role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독'
GROUP BY p.PersonID
ORDER BY SUM(m.Budget) DESC
LIMIT 1;

-- 13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.
SELECT p.Name, SUM(m.BoxOfficeWWGross) AS TotalRevenue
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN Role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우'
GROUP BY p.PersonID
ORDER BY SUM(m.BoxOfficeWWGross) DESC
LIMIT 1;

-- 14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)
SELECT Title, Budget, BoxOfficeWWGross
FROM Movie
WHERE Budget > 0
ORDER BY Budget ASC
LIMIT 1;

-- 15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요
SELECT AVG(BoxOfficeUSGross) AS AverageUSGross
FROM Movie
WHERE Budget <= 50000000;

-- 16. 액션 장르 영화의 평균 수익을 고르세요
SELECT AVG(m.BoxOfficeWWGross) AS AverageRevenue
FROM Movie m
JOIN MovieGenre mg ON m.MovieID = mg.MovieID
WHERE mg.GenreID = 4;

-- 17. 드라마, 전쟁 장르의 영화를 고르세요.
SELECT DISTINCT m.Title
FROM Movie m
JOIN MovieGenre mg ON m.MovieID = mg.MovieID
WHERE mg.GenreID IN (1, 19);

-- 18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.
SELECT m.Title, m.KoreanTitle, m.ReleaseYear
FROM Movie m
JOIN Appear a ON m.MovieID = a.MovieID
JOIN Person p ON a.PersonID = p.PersonID
WHERE p.KoreanName = '톰 행크스'
ORDER BY m.RunningTime DESC
LIMIT 1;

-- 19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
SELECT p.Name, COUNT(*) AS NumberOfAwards
FROM awardinvolve ai
JOIN Sector s ON ai.SectorID = s.SectorID
JOIN Appear a ON ai.AppearID = a.AppearID
JOIN Person p ON a.PersonID = p.PersonID
JOIN Winning w ON ai.WinningID = w.WinningID
WHERE s.SectorKorName = '남우주연상' AND w.WinOrNot = 'Winner'
GROUP BY p.PersonID
ORDER BY NumberOfAwards DESC
LIMIT 1;

-- 20. 아카데미상을 가장 많이 수상한 배우를 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)
SELECT p.Name, COUNT(*) AS NumberOfAwards
FROM awardinvolve ai
JOIN Appear a ON ai.AppearID = a.AppearID
JOIN Person p ON a.PersonID = p.PersonID
JOIN Winning w ON ai.WinningID = w.WinningID
JOIN Role r ON a.RoleID = r.RoleID
WHERE w.WinOrNot = 'Winner' AND p.Name != '수상자 없음' AND r.RoleKorName = '배우'
GROUP BY p.PersonID
ORDER BY NumberOfAwards DESC
LIMIT 1;

-- 21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
SELECT p.Name, COUNT(*) AS AwardsWon
FROM awardinvolve ai
JOIN Appear a ON ai.AppearID = a.AppearID
JOIN Person p ON a.PersonID = p.PersonID
JOIN Sector s ON ai.SectorID = s.SectorID
JOIN Winning w ON ai.WinningID = w.WinningID
WHERE s.SectorKorName = '남우주연상' AND WinOrNot = 'Winner'
GROUP BY p.PersonID
HAVING COUNT(*) >= 2
ORDER BY AwardsWon DESC;

-- 23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
SELECT p.Name, COUNT(*) AS NumberOfAwards 
FROM awardinvolve ai 
JOIN Appear a ON ai.AppearID = a.AppearID 
JOIN Person p ON a.PersonID = p.PersonID 
JOIN Winning w ON ai.WinningID = w.WinningID 
WHERE w.WinOrNot = 'Winner' AND p.Name != '수상자 없음' AND p.Name != 'John Doe'
GROUP BY p.PersonID 
ORDER BY NumberOfAwards DESC 
LIMIT 1;

-- 24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
SELECT m.Title, COUNT(*) AS NumberOfNominations
FROM awardinvolve ai
JOIN Appear a ON ai.AppearID = a.AppearID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN Winning w ON ai.WinningID = w.WinningID
WHERE w.WinOrNot = 'Nominated'
GROUP BY m.MovieID
ORDER BY NumberOfNominations DESC
LIMIT 1;

-- 25. 가장 많은 영화에 출연한 여배우를 고르세요.
SELECT p.Name, COUNT(*) AS NumberOfMovies
FROM Appear a
JOIN Person p ON a.PersonID = p.PersonID
JOIN Role r ON a.RoleID = r.RoleID
WHERE r.RoleName = 'Actress'
GROUP BY p.PersonID
ORDER BY NumberOfMovies DESC
LIMIT 1;

-- 26. 수익이 가장 높은 영화 TOP 10을 출력하세요.
SELECT Title, BoxOfficeWWGross
FROM Movie
ORDER BY BoxOfficeWWGross DESC
LIMIT 10;

-- 27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
SELECT Title, BoxOfficeWWGross, Budget
FROM Movie
WHERE BoxOfficeWWGross >= 1000000000 AND Budget <= 100000000;

-- 28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
SELECT p.Name, COUNT(*) AS WarMovieCount
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Role r ON a.RoleID = r.RoleID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN MovieGenre mg ON m.MovieID = mg.MovieID
JOIN Genre g ON mg.GenreID = g.GenreID
WHERE r.RoleName = 'Director' AND g.GenreName = 'War'
GROUP BY p.PersonID
ORDER BY WarMovieCount DESC
LIMIT 1;

-- 29. 드라마에 가장 많이 출연한 사람을 고르세요.
SELECT p.Name, COUNT(*) AS DramaMovieCount
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Role r ON a.RoleID = r.RoleID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN MovieGenre mg ON m.MovieID = mg.MovieID
JOIN Genre g ON mg.GenreID = g.GenreID
WHERE r.RoleKorName = '배우' AND g.GenreName = 'Drama'
GROUP BY p.PersonID
ORDER BY DramaMovieCount DESC
LIMIT 1;

-- 30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.
SELECT p.Name
FROM Person p
JOIN Appear a ON p.PersonID = a.PersonID
JOIN Movie m ON a.MovieID = m.MovieID
JOIN MovieGenre mg ON m.MovieID = mg.MovieID
JOIN Genre g ON mg.GenreID = g.GenreID
WHERE g.GenreName = 'Drama'
  AND NOT EXISTS (
    SELECT 1
    FROM Appear a2
    JOIN Movie m2 ON a2.MovieID = m2.MovieID
    JOIN MovieGenre mg2 ON m2.MovieID = mg2.MovieID
    JOIN Genre g2 ON mg2.GenreID = g2.GenreID
    WHERE a2.PersonID = a.PersonID
      AND g2.GenreName = 'Horror'
  );

-- 31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
SELECT Location, COUNT(*) AS CeremonyCount
FROM awardyear
GROUP BY Location
ORDER BY CeremonyCount DESC
LIMIT 1;

-- 33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
SELECT (YEAR(CURRENT_DATE()) - YEAR(MIN(Date))) AS YearsPassed
FROM awardyear;

-- 34. SF 장르의 영화 중 아카데미 영화제 후보에 가장 많이 오른 영화를 구하세요.
SELECT m.Title, COUNT(*) AS Nominations
FROM Movie m
JOIN MovieGenre mg ON mg.MovieID = m.MovieID
JOIN Genre g ON g.GenreID = mg.GenreID
JOIN Appear a ON a.MovieID = m.MovieID
JOIN AwardInvolve ai ON ai.AppearID = a.AppearID
JOIN Winning w ON w.WinningID = ai.WinningID
WHERE g.GenreName = 'Sci-Fi' AND w.WinOrNot = 'Nominated'
GROUP BY m.Title
ORDER BY Nominations DESC
LIMIT 1;

-- 35. 드라마 장르의 영화의 아카데미 영화제 작품상 수상 비율을 구하세요.
SELECT COUNT(CASE WHEN w.WinOrNot = 'Winner' THEN 1 END) / COUNT(*) * 100 AS Winning_Ratio
FROM Winning w
JOIN Awardinvolve ai ON w.WinningID = ai.WinningID
JOIN Appear a ON ai.AppearID = a.AppearID
JOIN Movie m ON m.MovieID = a.MovieID
JOIN MovieGenre mg ON mg.MovieID = m.MovieID
JOIN Genre g ON g.GenreID = mg.GenreID
WHERE g.GenreName = 'Drama';