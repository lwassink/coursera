class RabinKarp(text: String) {
  val R = 256
  val q = 2147483647
  val chars = text.toCharArray

  def hash(string: String): Int = string.foldLeft(0)((acc, char) => (R * acc + char) % q)

  def calcH(num: Int): Int = if (num == 1) 1 else (R * calcH(num - 1)) % q

  def search(pat: String): List[Int] = {
    val pHash = hash(pat)
    val m = pat.length
    val t0 = if (m > chars.length) 0 else hash(text.substring(0, m))
    val h = calcH(pat.length)
    val max = chars.length - m

    def loop(num: Int, tHash: Int, locations: List[Int]): List[Int] =
      if (num >= chars.length - m) {
        if (pHash == tHash && text.slice(num, num + m) == pat) num :: locations
        else locations
      } else {
        loop(
          num + 1,
          (R * (tHash - chars(num) * h) + chars(num + m)) % q,
          if (pHash == tHash && text.substring(num, num + m) == pat) num :: locations else locations
        )
      }

    loop(0, t0, Nil).reverse
  }
}


val rk = new RabinKarp("abaabbcab")
rk.search("ab")
rk.search("bc")
rk.search("q")
rk.search("qab")
rk.search("aaaaaaaaaaaaaaaaaaaa")
val rk2 = new RabinKarp("aaaaaa")
rk2.search("aaa")

