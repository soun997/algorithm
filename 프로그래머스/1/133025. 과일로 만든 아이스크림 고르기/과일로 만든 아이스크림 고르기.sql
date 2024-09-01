SELECT ICECREAM_INFO.FLAVOR AS FLAVOR
FROM ICECREAM_INFO INNER JOIN FIRST_HALF ON ICECREAM_INFO.FLAVOR = FIRST_HALF.FLAVOR
WHERE INGREDIENT_TYPE = 'fruit_based' AND TOTAL_ORDER >= 3000
ORDER BY TOTAL_ORDER DESC;
