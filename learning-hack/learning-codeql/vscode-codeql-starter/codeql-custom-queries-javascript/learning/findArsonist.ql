import tutorial

// https://www.4hou.com/posts/J7k9

predicate southern(Person p) {
    p.getLocation() = "south"
}

class Southerner extends Person {
    Southerner() {
        // this 表示一个Person类型的值
        southern(this)
    }
}

from Person p
where southern(p)
select p