package berry.examples.com.QA

object ListQ1 extends  App {

  def  generateAllPermutation(a: String) = {

    val ip = a

    val substrings = for{
      i <- 0 until ip.length
      j <- i+1 to ip.length
    }yield ip.slice(i,j)

    val permutations = for{
      i <- substrings.indices
      j <- substrings.indices
      if i != j
    }yield substrings(i)+substrings(j)


    val res = permutations.toSet

    println(res.toList.sorted)
  }


  generateAllPermutation("abc")
}
