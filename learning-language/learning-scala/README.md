# how to use idea maven to create a scala module

1.install plugin -> scala  
2.create module -> spring initializer  
3.add scala library  
download here: [github-lampepfl](https://github.com/lampepfl/dotty/tags) then goto project structure -> modules -> dependencies -> +  
4.create scala dir in main dir   
5.new scala file -> cv -> `control + shift + r` => run!  

```scala
object HelloWorld {
  /* 这是我的第一个 Scala 程序
    * 以下程序将输出'com.xjjlearning.scala.Hello World!'
    */
  def main(args: Array[String]) = {
    println("com.xjjlearning.scala.Hello, world!") // 输出 com.xjjlearning.scala.Hello World
  }
}
```