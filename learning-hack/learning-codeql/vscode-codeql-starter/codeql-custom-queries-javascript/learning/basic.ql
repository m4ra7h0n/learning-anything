import javascript

// 1.example

// from BlockStmt b
// where b.getNumStmt() = 0
// select b, "This is an empty block."

// 2.int

// from int xjj
// where xjj = -2
// select xjj.abs().sqrt()

// from float x, int y
// where x = 3.6 and y = 3
// select x.pow(y)


// 3.string

// from string s
// where s = "x\"jj"
// select s.toUpperCase()


// 4.date
// from date start, date end
// where start = "01/01/2023".toDate() 
// and end = "04/01/2023".toDate()
// select start.daysTo(end), start.getMonth()


// 5.boolean
// from boolean b
// where b = false
// select b.booleanNot()


// 6.comment
from Comment c 
where c.getText().regexpMatch("(?si).*\\bTODO\\b.*") 
select c