import tutorial

// https://www.4hou.com/posts/nN9D

from Person p
where p.getHeight() > 150 
and not p.getHairColor() = "blond" 
// QL语言中exists对应于逻辑学中的存在量词，表示“存在某个”或“至少有一个”。 此外，QL语言中还提供了一个通用量词，即forall，表示“所有的”，或“每一个”。
and exists(string c | p.getHairColor() = c)
and not p.getAge() < 30
and p.getLocation() = "east"
and (p.getHairColor() = "black" or p.getHairColor() = "brown")
and not (p.getHeight() > 180 and p.getHeight() < 190)
and exists(Person p1 | p1.getAge() > p.getAge())

// 聚合操作
// 求最大的年龄:  max( 定义 | 限制 | 取值/排序 )
// max(int i | exists(Person p | p.getAge() = i) | i)

and not p = max(Person p1 | | p1 order by p1.getAge())
and p.getHeight() < avg(float i | exists(Person p1 | p1.getHeight() = i) | i)
and p = max(Person p1 | p1.getLocation() = "east" | p1 order by p1.getAge())
select "The thief is " + p + "!"