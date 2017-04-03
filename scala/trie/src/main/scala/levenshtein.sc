object lev {
  def dist(xs: String, ys: String): Int =
    if (math.min(xs.length, ys.length) == 0) math.max(xs.length, ys.length)
    else List(
      dist(xs.init, ys) + 1,
      dist(xs, ys.init) + 1,
      dist(xs.init, ys.init) + (if (xs.last == ys.last) 0 else 1)
    ).min
}

lev.dist("kitten", "sitting")
lev.dist("flaw", "lawn")