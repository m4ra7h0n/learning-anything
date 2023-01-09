import python

// 找到没有else并且if下面只有一个pass的if

// from If ifstmt, Stmt pass
// where
//   pass = ifstmt.getStmt(0) and
//   pass instanceof Pass and
//   not exists(ifstmt.getOrelse())
// select ifstmt, "This 'if' statement is redundant."

// 找到作用域是class的function

// from Function f
// where f.getScope() instanceof Class
// select f

// 语句 Stmt
// 语句由Stmt表示
// for 下面只有一个pass

// from For f, Stmt pass
// where f.getStmt(0) = pass and pass instanceof Pass
// select f.getBody(), f.getIter()

// 表达式 Expr
// 查找 表达式: 变量 表达式 数字
from BinaryExpr bin
where bin.getLeft() instanceof Name and bin.getRight() instanceof Num
select bin

// 语句与表达式的关系
// http://python.86x.net/02expression/index.html

// 查找所有的except 自带有pass
// from ExceptStmt ex
// where not exists(Stmt s | s = ex.getAStmt() | not s instanceof Pass)
// select ex