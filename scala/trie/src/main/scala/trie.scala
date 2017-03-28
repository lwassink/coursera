/**
  * Created by lwassink on 3/15/17.
  *
  **/

abstract class TrieType {
  val isLeaf: Boolean
  def isEmpty: Boolean
  def contains(word: String): Boolean
  val children: Array[Trie]
  val char: Char
}

class Trie(val char: Char, val children: Array[Trie], val isLeaf: Boolean) extends TrieType {
  def contains(word: String): Boolean =
    if (word.length == 0) true
    else if (word.length() == 1) this.findChild(word(0)).isLeaf
    else this.findChild(word(0)).contains(word.substring(1))

  def findChild(char: Char): TrieType = {
    def findChild(list: Array[Trie]): TrieType =
      if (list.length == 0) new EmptyTrie
      else if (list(0).char == char) list(0)
      else findChild(list.drop(1))

    findChild(children)
  }

  def findIndex(char: Char): Integer = {
    def findIndex(list: Array[Trie], idx: Integer): Integer =
      if (list.length == 0) throw new IndexOutOfBoundsException
      else if (list(0).char == char) idx
      else findIndex(list.drop(1), idx + 1)

    findIndex(children, 0)
  }

  def addChild(t: Trie): Trie = {
    val child = findChild(t.char)
    if (child.isEmpty) new Trie(this.char, this.children ++ Array(t), this.isLeaf)
    else {
      val idx = findIndex(t.char)
      val newChildren = children.slice(0, idx) ++
        Array(new Trie(child.char, child.children, child.isLeaf)) ++
        children.drop(idx + 1)
      new Trie(char, newChildren, isLeaf)
    }
  }

  def insert(word: String): Trie = {
    if (word.length == 0) return this

    val first = word(0)
    val rest = word.drop(1)

    if (rest == "") {
      this.addChild(new Trie(first, Array(), this.findChild(first).isLeaf))
    } else {
      this.addChild(new Trie(first, Array(), true).insert(rest))
    }
  }

  def isEmpty = false
}

class EmptyTrie extends TrieType {
  val isLeaf = false
  def isEmpty = true
  def contains(word: String) = false
  val children = Array()
  val char = throw new IllegalArgumentException
}

class Dictionary {
  var root = new Trie('a', Array(), false)

  def addWord(word: String): Unit = {
    root = root.insert(word)
  }

  def contains(word: String): Boolean = root.contains(word)
}