package trie2

/**
  * Created by lwassink on 3/20/17.
  */
class Trie[Value >: Null] {
  private val R = 256
  private var root = new Node[Value](null)

  private class Node[Value](val value: Value, val children: Array[Node[Value]]) {
    def this(value: Value) = this(value, new Array[Node[Value]](256))
  }

  def get(key: String): Value = {
    val x = get(key, this.root, 0)
    if (x == null) null
    else x.value
  }

  private def get(key: String, node: Node[Value], pos: Integer): Node[Value] =
    if (node == null) null
    else if (pos == key.length()) node
    else get(key, node.children(key.charAt(pos)), pos + 1)

  def put(key: String, value: Value): Null = {
    root = put(key, value, root, 0)
    null
  }

  private def put(key: String, value: Value, node: Node[Value], pos: Integer): Node[Value] = {
    if (pos == key.length()) {
      if (node == null) new Node(value)
      else new Node(value, node.children)
    } else {
      val c = key.charAt(pos)
      val newChildren = new Array[Node[Value]](256)
      if (node != null) Array.copy(node.children, 0, newChildren, 0, 256)
      newChildren(c) = put(key, value, newChildren(c), pos + 1)

      val newValue = if (node == null) null else node.value
      new Node[Value](newValue, newChildren)
    }
  }

  def keys(node: Node[Value]): List[String] = {
    def collect(node: Node[Value], pre: String): List[String] = node match

    collect(root, "")
  }
}

object Main extends App {
  val t = new Trie[String]
  t.put("monk", "timmy")
  t.put("monkey", "judith")
  t.put("monktoon", "lilith")
  t.put("app", "awesome")

  println(t.get("m"))
  println(t.get("mo"))
  println(t.get("mon"))
  println(t.get("monk"))
  println(t.get("monke"))
  println(t.get("monkey"))
  println(t.get("mpp"))
}
