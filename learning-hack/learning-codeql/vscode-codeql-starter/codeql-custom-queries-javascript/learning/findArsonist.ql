import tutorial

// https://www.4hou.com/posts/J7k9
predicate southern(Person p) { p.getLocation() = "south" }
predicate bald(Person p) {
    not exists(string s | p.getHairColor() = s)
}

// class 为逻辑属性 并不创建任何属性
// Southerner: 子集, Person: 超集
class Southerner extends Person {
  Southerner() {
    // this 表示一个Person类型的值
    southern(this)
  }
}

class Child extends Person {
  Child() { this.getAge() < 10 }

  // 孩子只能在自己的地盘上走动
  override predicate isAllowedIn(string region) { region = this.getLocation() }
}

from Southerner p
// 当该person年龄小于10的时候 自动执行Child的逻辑
where p.isAllowedIn("north")
and bald(p)
select p, p.getAge()
