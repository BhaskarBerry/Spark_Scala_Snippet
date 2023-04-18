package berry.examples.com.StringsQ

import scala.annotation.tailrec

object PalindromeCheck extends  App {

 //Using string conversion and comparison (O(n)):
  def isPalindrome(num: Int): Boolean = {
    num.toString == num.toString.reverse
  }


  // Using Recursive Function
  def isPalindromeRec(num: Int): Boolean ={
    def helper(n: Int, reversed: Int): Boolean={
      if (n==0) reversed== num
      else helper(n / 10, reversed * 10 + n % 10)
    }
    helper(num,0)
  }

  // Using tail recursion Recursive Function
  def isPalindromeRec1(num: Int): Boolean ={
    @tailrec
    def helper(n: Int, reversed: Int): Boolean={
      if (n==0) reversed== num
      else helper(n / 10, reversed * 10 + n % 10)
    }
    helper(num,0)
  }

// USing While Loop
  def isPalindromic(num: Int): Boolean = {
    var n = num
    var reversed = 0
    while (n > 0) {
      reversed = reversed * 10 + n % 10
      n /= 10
    }
    num == reversed
  }


  val x  =  scala.io.StdIn.readLine("Enter the String: ").toInt

  println("Using Reverse : "+ isPalindrome(x))
  println("Using Recursive : "+ isPalindromeRec(x))
  println("Using tail Recursive : "+ isPalindromeRec1(x))
  println("Using While loop : "+ isPalindromic(x))

}

