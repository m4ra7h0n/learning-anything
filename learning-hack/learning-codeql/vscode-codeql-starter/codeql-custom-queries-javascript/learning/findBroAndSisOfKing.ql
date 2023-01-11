import tutorial

Person childOf(Person p) {
    p = parentOf(result)
}

// 祖先的两种表示方法
// 1
Person ancestorOfA(Person p) {
    result = parentOf(p) or
    result = parentOf(ancestorOfA(p))
}
// 2
Person ancestorOfB(Person p) {
    result = parentOf+(p)
}

Person relativeOf(Person p) {
    parentOf*(p) = parentOf*(result)
}

predicate criminalRecord(Person p) {
    p = "Hester" or
    p = "Hugh" or
    p = "Charlie"
}

// from Person p
// where parentOf(p) = parentOf("King Basil")
// and not p = "King Basil"
// and not p.isDeceased()
// select p

from Person p
where not p.isDeceased()
and relativeOf("King Basil") = p
and not criminalRecord(p)
select p
