class Node
  attr_accessor :content, :next

  include Enumerable

  def initialize(content = nil)
    @content = content
    @next = nil
  end

  def self.merge(x, y, compare)
    if compare.call(x.content, y.content) < 0
      start = x
      x = x.next
    else
      start = y
      y = y.next
    end

    current = start

    until x.nil? && y.nil?
      if y && (x.nil? || compare.call(y.content, x.content) < 0)
        current.next = y
        y = y.next
      else
        current.next = x
        x = x.next
      end

      current = current.next || current
    end

    start
  end

  def self.sort(left, length = nil, &compare)
    length ||= left.length
    compare ||= Proc.new { |x, y| x <=> y }

    return left if length == 1
    mid = length / 2

    right = left
    (mid - 1).times { right = right.next }
    pre_right = right
    right = right.next
    pre_right.next = nil

    left = self.sort(left, mid, &compare)
    right = self.sort(right, length - mid, &compare)

    self.merge(left, right, compare)
  end

  def to_s
    output = []
    self.each do |node|
      output.push(node.content)
    end
    output.join(' : ')
  end

  def inspect
    to_s
  end

  def each(&prc)
    x = self
    until x.nil?
      prc.call(x)
      x = x.next
    end
    self
  end

  def length
    x = self
    length = 0
    until x.nil?
      length += 1
      x = x.next
    end
    length
  end
end

if __FILE__ == $PROGRAM_NAME
  s = Node.new(rand(100))
  n = s
  10.times do
    n.next = Node.new(rand(100))
    n = n.next
  end

  s = Node.sort(s)
  puts "Original"
  puts s
  puts "Randomized"
  p Node.sort(s) { [1, -1].sample }
end

