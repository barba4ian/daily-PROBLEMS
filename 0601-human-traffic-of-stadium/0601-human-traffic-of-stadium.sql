SELECT DISTINCT s1.id, s1.visit_date, s1.people
FROM Stadium s1
WHERE s1.people >= 100
  AND (

    -- Case 1: s1 is part of (id, id+1, id+2)

    (SELECT COUNT(*) FROM Stadium WHERE id IN (s1.id, s1.id+1, s1.id+2) AND people >= 100) = 3
    OR

    -- Case 2: s1 is part of (id-1, id, id+1)

    (SELECT COUNT(*) FROM Stadium WHERE id IN (s1.id-1, s1.id, s1.id+1) AND people >= 100) = 3
    OR

    -- Case 3: s1 is part of (id-2, id-1, id)
    
    (SELECT COUNT(*) FROM Stadium WHERE id IN (s1.id-2, s1.id-1, s1.id) AND people >= 100) = 3
  )
ORDER BY s1.visit_date;