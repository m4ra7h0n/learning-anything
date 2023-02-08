fun main(args: Array<String>) {    // 包级可见的函数，接受一个字符串数组作为参数
    println("Hello World!")         // 分号可以省略
    println(sum(1, 2))
    vars(1, 2, 3)
}

//fun sum(a: Int, b: Int): Int {
//    return a + b
//}

fun sum(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int) {
    print(a + b)
}

fun vars(vararg v: Int) {
    for (vt in v) {
        print(vt)
    }
}