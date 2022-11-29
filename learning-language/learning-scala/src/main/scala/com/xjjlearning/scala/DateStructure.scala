package com.xjjlearning.scala

import java.io.File
import java.net.URL
import scala.io.Source

object DateStructure {

  def f(x: Int): Int = x + 3

  def main(args: Array[String]): Unit = {

    // var -> readable & writable
    // val -> r only
    var authorsToAge = Map("xjj" -> 22, "Raoul" -> 23, "Mario" -> 40)
    val authors = List("xjj", "Raoul", "Mario")
    val number = Set(1, 2, 3, 5, 8) //

    // structure read only by default,
    // when create a new structure from an old one, they share mostly of elements
    // unmodifiable or immutable(yes)
    val newNumbers = number + 10 // create a new set from numbers

    authorsToAge.+("abc" -> 15) // :)

    // read file
    val resource :URL = this.getClass.getClassLoader.getResource("")
    val file = resource.getPath + "data"
    val source = Source.fromFile(file)

    // stream function
    val lines = source.getLines().toList
    println(lines)
    val filterLine = lines.filter(line => line.length < 20)
    println(filterLine)
    val upperLine = filterLine.map(line => line.toUpperCase())
    println(upperLine)
    // it can be done, too
    val linesLongUpper = lines filter (_.length() < 20) map(_.toUpperCase())
    println(linesLongUpper)

    // tuple
    val raoul = ("Raoul", "+ 44 887007007")
    val alan = ("Alan", "+44 883133700")
    val book = (2014, "Java 8 in Action", "Manning")
    val numbers = (42, 1337, 0, 3, 14)
    println(book._1)
    println(numbers._4)

    // test func
    def isJavaMentioned(tweet: String) : Boolean = tweet.contains("Java")
    def isShortTweet(tweet: String) : Boolean = tweet.length() < 20
    val tweets = List(
      "I love the new features in Java 8",
      "How's it going?",
      "An SQL query walks into a bar, sees two tables and says 'Can I join you?'"
    )
    tweets.filter(isJavaMentioned).foreach(println);
    tweets.filter(isShortTweet).foreach(println);


    // Anonymous expression
    // 'String => Boolean' is the type of var 'isLongTweet'
    val isLongTweet : String => Boolean = (tweet : String) => tweet.length() > 60
    val isLongTweetOrin : String => Boolean = new Function1[String, Boolean] {
      def apply(tweet: String) : Boolean = tweet.length() > 60
    }
    println(isLongTweet.apply("tweet.length() > 60"))  //:)
    println(isLongTweetOrin.apply("tweet.length() > 60"))


    // Nature of closure, this will throw Exception in java
    var count = 0
    val inc = () => count += 1
    inc(); println(count)
    inc(); println(count)


    // Corialization
    def multiply(x : Int, y: Int): Int = x * y
    val r = multiply(2, 10);
    def multiplyCurry(x :Int)(y : Int) = x * y
    val r1 = multiplyCurry(2)(10)


    // test class
    class Hello {
      def sayThankYou(): Unit = {
        class H {} //:)
        println("Thanks for staring my github: https://github.com/x-j-j/learning-anything")
      }
    }
    val h = new Hello()
    h.sayThankYou()


    // test getter and setter
    class Student(var name: String, var id: Int)
    val s = new Student("Raoul", 1)
    // getter
    println(s.name)
    // setter
    s.id = 1337
    println(s.id)



    // test trait
    trait Sized{
      val size: Int = 0
      def isEmpty: Boolean = size == 0
    }
    class Empty extends Sized
    println(new Empty().isEmpty)

    class Box
    val b1 = new Box() with Sized
    println(b1.isEmpty)
    val b2 = new Box()
//    b2.isEmpty

  }

  //  def getCarInsuranceName(person: Option[Person], minAge: Int) = person.filter(_.getAge() >= minAge)
  //    .flatMap(_.getCar)
  //    .flatMap(_.getInsurance)
  //    .map(_.getName).getOrElse("Unknown")

}
