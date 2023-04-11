package berry.examples.com.DataStructure

import java.util.Stack

class MinStackDemo {
  private val s = new Stack[Node]

  private class Node(val value: Int, val min: Int)

  def push(x: Int): Unit = {
    if(s.isEmpty()){
      s.push(new Node(x,x))
    }
    else{
      val min = Math.min(s.peek().min, x)
      s.push(new Node(x,min))
    }
  }

  def pop(): Int = {
    s.pop().value
  }

  def top(): Int = {
    s.peek().value
  }

  def getMin(): Int = {
    s.peek().min
  }
}

object MinStackDemo{
  def main(args: Array[String]): Unit = {
    val s = new MinStackDemo()
    s.push(-1)
    s.push(10)
    s.push(-4)
    s.push(0)
    println(s.getMin())
    println(s.pop())
    println(s.pop())
    println(s.getMin())
  }
}
