package berry.examples.com.DataStructure

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Constraints:
-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.

*/

//object minStack {
//
//}

object stackDemo extends App {
//  class minStack() {
//    private val stack = scala.collection.mutable.Stack[Int]()
//    private val minStack = scala.collection.mutable.Stack[Int]()
//
//    def push(x: Int): Unit = {
//      stack.push(x)
//      if (minStack.isEmpty || x <= minStack.top) minStack.push(x)
//    }
//
//    def pop(): Unit = {
//      if (stack.top == minStack.top) minStack.pop()
//      stack.pop()
//    }
//
//    def top(): Int = stack.top
//
//    def getMin(): Int = minStack.top
//  }
//  // Output
//  println("=============================MinStack Method using two Stack=============================")
//  val minstack = new minStack()
//  minstack.push(-2);
//  minstack.push(0);
//  minstack.push(-3);
//  println("getMin=", minstack.getMin()); // return -3
//  println("Pop=", minstack.pop());
//  println("get Top=", minstack.top());    // return 0
//  println("getMin=", minstack.getMin()); // return -2
//

  // Using single stack with tuple
  class MinStack(){
    private val stack = scala.collection.mutable.Stack[(Int, Int)]()
    private var min = Int.MaxValue

    def push(x: Int): Unit = {
      min = math.min(x, min)
      stack.push((x, min))
    }

    def pop(): Unit = stack.pop()

    def top(): Int = stack.top._1

    def getMin(): Int = stack.top._2
  }

  // Output
  println("=============================MinStack Method=============================")
  val minstack1 = new MinStack()
  minstack1.push(-2);
  minstack1.push(0);
  minstack1.push(-3);
  println("getMin=", minstack1.getMin()); // return -3
  println("Pop=", minstack1.pop());
  println("get Top=", minstack1.top());    // return 0
  println("getMin=", minstack1.getMin()); // return -2
}


