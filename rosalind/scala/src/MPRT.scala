import scala.io.Source
import scala.util.matching.Regex

object MPRT {
  def main(args: Array[String]) : Unit = {
    val lines = Source.stdin getLines () toArray
    val fasta = lines.map(s => ( s, Source fromURL("http://www.uniprot.org/uniprot/" + s + ".fasta") mkString ) )
    val prot = fasta.map{
      case (name,text) => ( name, Source fromString text getLines () drop 1 mkString "" )
    }
    val regex = "N[^P][ST][^P]".r
    val posn = prot.map {
      case (name,s) => { // we need to handle overlapping matches
        lazy val matchStream: Stream[(Option[Regex.Match], Int)] = (regex findFirstMatchIn s, 0) #:: { matchStream map {
          case (None, k) => (None, k)
          case (Some(m), k) => (regex findFirstMatchIn (s.substring(k + m.start + 1)), k + m.start + 1)
        } }
        (name, matchStream.takeWhile(_._1 != None).map { case (Some(it), k) => it.start + k + 1 })
      }
    } filter { case (_,str) => ! str.isEmpty}
    posn foreach { p => { println(p _1); println(p._2 mkString " ") } }
  }
}
