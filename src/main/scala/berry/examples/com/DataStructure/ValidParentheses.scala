package berry.examples.com.DataStructure

import scala.collection.mutable.Stack

//Valid Parentheses In a string
//Imperative Solution  - Using Stack
object ValidParentheses extends App {
  def isValid(s: String): Boolean = {
    val stack = Stack[Char]()
    val brackets = Map('(' -> ')', '{' -> '}', '[' -> ']')

    for (c <- s) {
      if (brackets.keySet.contains(c)) {
        stack.push(c)
      } else if (brackets.values.toSet.contains(c)) {
        if (stack.isEmpty || brackets(stack.pop()) != c) {
          return false
        }
      }
    }
    stack.isEmpty
  }

  println(isValid("()"))
  println(isValid("(]"))


  //Recursive Solution
  def isValidRecursive(s: String): Boolean = {
    def isMatched(c1: Char, c2: Char): Boolean =
      (c1, c2) match {
        case ('(' -> ')' | '{' -> '}' | '[' -> ']') => true
        case _ => false
      }

    def isValidHelper(s: List[Char], stack: List[Char]): Boolean =
      (s, stack) match {
        case (Nil, Nil) => true
        case (h :: t, _) if "({[".contains(h) => isValidHelper(t, h :: stack)
        case (h :: t, sh :: st) if isMatched(sh, h) => isValidHelper(t, st)
        case _ => false
      }

    isValidHelper(s.toList, Nil)
  }

  println("is Valid Recursive: ", isValidRecursive("()"))
  println("is Valid Recursive: ", isValidRecursive("(]"))

  // FoldLeft Functions
  def isValidF(s: String): Boolean = {
    val brackets = Map('(' -> ')', '{' -> '}', '[' -> ']')

    s.foldLeft(List[Char]()) { (stack, c) =>
      if (brackets.keySet.contains(c)) {
        c :: stack
      } else if (brackets.values.toSet.contains(c)) {
        stack match {
          case h :: t if brackets(h) == c => t
          case _ => return false
        }
      } else {
        stack
      }
    }.isEmpty
  }

  println("is Valid FoldLeft: ", isValidF("()"))
  println("is Valid FoldLeft: ", isValidF("(]"))
}

