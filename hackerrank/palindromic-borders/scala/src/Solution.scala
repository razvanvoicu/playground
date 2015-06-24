import java.util.Scanner

object Solution {

    val moduloConstant = 1000000007L

    sealed trait NodeBase {
        val length: Int = -1
        var suffix : NodeBase = this
        val nexts : Array[NodeBase] = Array(Nil,Nil,Nil,Nil,Nil,Nil,Nil,Nil)
        var count : Long = 0
    }

    case object Nil extends NodeBase

    case class Node(override val length:Int, _suffix:NodeBase) extends NodeBase {
        def this(length:Int) = this(length,Nil)
        override val nexts : Array[NodeBase] = Array(Nil,Nil,Nil,Nil,Nil,Nil,Nil,Nil)
        suffix = if (_suffix == Nil) this else _suffix
    }

    def main(args: Array[String]) {
        val in = new Scanner(System.in)
        val builder = new PalindromicSuffixesBuilder(in.nextLine())
        println(builder.getSuffixCount)
    }

    class PalindromicSuffixesBuilder(input:String) {
        var nodes : Array[Node] = new Array(input.length)
        val root = new Node(0,new Node(-1))
        var currentNode : NodeBase = root
        var nodeCounter = 0

        0.to(input.length - 1).map {
            i => {
                traversePrefixesAndFindPalindrome(i)
                val currentInputElement = inputCharAt(i)
                val currentNextIsNil = currentNode.nexts(currentInputElement) == Nil
                if (currentNextIsNil) {
                    val nextSuffix = computeNextSuffix(i)
                    val l = currentNode.length + 2
                    val n = Node(l,nextSuffix)
                    currentNode.nexts(currentInputElement) = n
                    nodes(nodeCounter) = n
                    nodeCounter += 1
                }
                currentNode = currentNode.nexts(currentInputElement)
                currentNode.count += 1
            }
        }

        def inputCharAt(index:Int) : Int = {
            if (index >= 0) input.charAt(index) - 'a'
            else 8
        }

        def notPalindrome(index:Int, node: NodeBase) = {
            val length = node.length
            val left = inputCharAt(index-length-1)
            val right = inputCharAt(index)
            left != right
        }

        def traversePrefixesAndFindPalindrome(index:Int) = {
            while (notPalindrome(index,currentNode))
                currentNode = currentNode.suffix
        }

        def computeNextSuffix(index: Int): NodeBase = {
            if (currentNode.length == -1)
                root
            else {
                var suf = currentNode.suffix
                while (notPalindrome(index,suf))
                    suf = suf.suffix
                suf.nexts(inputCharAt(index))
            }
        }

        def getSuffixCount = {
            var total = 0L
            while (nodeCounter > 0) {
                nodeCounter -= 1;
                val node = nodes(nodeCounter)
                node.suffix.count += node.count
                total += node.count * (node.count-1) / 2
                total %= moduloConstant
            }
            total
        }
    }
}