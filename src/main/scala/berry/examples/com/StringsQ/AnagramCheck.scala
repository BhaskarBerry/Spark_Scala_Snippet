package berry.examples.com.StringsQ

object AnagramCheck {

  def main(args: Array[String]): Unit = {

    println("Anagram Check start-- Using Sorted")
    println(areAnagrams("anagram", "margana"))
    println(areAnagrams("bose", "bery"))
    println("Anagram Check End-- Using Sorted")
    println("Anagram Check start -- Using Frequency")
    println(freAnagrams("anagram", "margana"))
    println(freAnagrams("bose", "berry"))
    println("Anagram Check End -- Using Frequency")

    println("Anagram Check start -- Using XOR")
    println(xorAnagrams("anagram", "margana"))
    println(xorAnagrams("bose", "berry"))
    println("Anagram Check End -- Using XOR")
  }

  // Using Sorting
  def areAnagrams(s: String, t: String): Boolean = {
    s.sorted == t.sorted
  }

  // use frequency Counting
  def freAnagrams(s: String, t: String): Boolean = {
    if (s.length != t.length)
      false
    else {
      val sFrequency = s.groupBy(identity).view.mapValues(_.length).toMap
      val tFrequency = t.groupBy(identity).mapValues(_.length).toMap

      sFrequency == tFrequency
    }
  }

  //Use XoR -- If it is not ASCII character we need to Normalize the characters
  def xorAnagrams(s: String, t: String): Boolean = {
    if (s.length != t.length) return  false

    val xorResult = s.zip(t).map{
      case (c1, c2) => c1.toInt^c2.toInt
    }  .foldLeft(0)(_^_)
    xorResult == 0
  }
}
