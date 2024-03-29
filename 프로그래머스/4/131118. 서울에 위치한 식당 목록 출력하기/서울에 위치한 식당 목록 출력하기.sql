-- 코드를 입력하세요
SELECT B.REST_ID,
    B.REST_NAME,
    B.FOOD_TYPE,
    B.FAVORITES,
    B.ADDRESS,
    A.SCORE
FROM
    (
        SELECT REST_ID, ROUND(AVG(REST_REVIEW.REVIEW_SCORE),2) AS SCORE
        FROM REST_REVIEW
        GROUP BY REST_ID
    ) AS A
    JOIN REST_INFO B
    ON A.REST_ID = B.REST_ID
WHERE ADDRESS LIKE ('서울%')
ORDER BY SCORE DESC, FAVORITES DESC