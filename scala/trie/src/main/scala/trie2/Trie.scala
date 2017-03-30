package trie2

/**
  * Created by lwassink on 3/20/17.
  */
class Trie[Value >: Null] {
  private val R = 256
  private var root = new Node(null)

  private case class Node(val value: Value, val children: Array[Node]) {
    def this(value: Value) = this(value, new Array[Node](256))
  }

  def get(key: String): Value = {
    val x = get(key, this.root, 0)
    if (x == null) null
    else x.value
  }

  private def get(key: String, node: Node, pos: Integer): Node =
    if (node == null) null
    else if (pos == key.length()) node
    else get(key, node.children(key.charAt(pos)), pos + 1)

  def put(key: String, value: Value): Null = {
    root = put(key, value, root, 0)
    null
  }

  private def put(key: String, value: Value, node: Node, pos: Integer): Node = {
    if (pos == key.length()) {
      if (node == null) new Node(value)
      else new Node(value, node.children)
    } else {
      val c = key.charAt(pos)
      val newChildren = new Array[Node](256)
      if (node != null) Array.copy(node.children, 0, newChildren, 0, 256)
      newChildren(c) = put(key, value, newChildren(c), pos + 1)

      val newValue = if (node == null) null else node.value
      new Node(newValue, newChildren)
    }
  }

  def keys: List[String] = keysWithPrefix("")

  def keysWithPrefix(pre: String): List[String] = collect(get(pre, root, 0), pre)

  def collect(x: Node, pre: String): List[String] = {
    def loopThroughChildren(char: Int): List[String] =
      if (char >= R) Nil
      else collect(x.children(char), pre + char.asInstanceOf[Char]) ++ loopThroughChildren(char + 1)

    if (x == null) Nil
    else if (x.value == null)   loopThroughChildren(0)
    else                        pre :: loopThroughChildren(0)
  }
}

object Main extends App {
  val t = new Trie[String]
  t.put("monk", "timmy")
  t.put("monkey", "judith")
  t.put("monktoon", "lilith")
  t.put("app", "awesome")
  t.put("molo", "pink")

  println(t.get("m"))
  println(t.get("mo"))
  println(t.get("mon"))
  println(t.get("monk"))
  println(t.get("monke"))
  println(t.get("monkey"))
  println(t.get("mpp"))
  println(t.keys)
  println(t.keysWithPrefix("m"))
  println(t.keysWithPrefix("mo"))
  println(t.keysWithPrefix("monk"))
}
