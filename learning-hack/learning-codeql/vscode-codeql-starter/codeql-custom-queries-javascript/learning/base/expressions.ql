import tutorial

// select sum(int i, int j | i in [0 .. 3] and j in [2 .. 4] | i * j)
// select avg(int i | i in [0 .. 5] | i)
// select min(string s | s = ["Tarski", "Dedekind", "De Morgan"] | s)
// select concat(int i | i in [0 .. 4] | i.toString(), "|" order by i desc)
// select rank[4](int i | i = [5 .. 15] | i order by i desc)
// select sum(int i, int j | 
//     exists(string s | s = "hello".charAt(i))
//     and exists(string s | s = "world!".charAt(j))
//  | i)


from boolean b
where b = false
select b.booleanAnd(true)
