import tutorial

// 小于100的整数
int getANumber() {
  // 集合包含0
  result = 0
  or
  // 集合也包含如下
  result <= 100 and result = getANumber() + 1
}

// 基数
int getAnOdd() { result = getAnEven() + 1 }

// 偶数
int getAnEven() {
  result = 0
  or
  result <= 100 and result = getAnOdd() + 1
}

// 灭绝
class Extinct extends Person {
  predicate isExtinct() {
    this.isDeceased() and
    // 偶数次否定没事 基数次不行
    not exists(Extinct descendant | descendant.getAParent+() = this | not descendant.isExtinct())
  }
}

select 1
