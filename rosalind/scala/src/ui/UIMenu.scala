package ui

import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control._
import javafx.scene.input.MouseEvent
import javafx.scene.layout.VBox
import scala.xml._

object UIMenu {
  val linkStyle = "-fx-text-fill: darkblue; -fx-underline: false;"
  val highlight = " -fx-background-color: lightgray;"

  def problemName(c: Elem) = c.attribute("name") match {
    case Some(v) => v.head.text;
    case None => null
  }

  def problemCode(c: Elem) = c.attribute("class") match {
    case Some(v) => v.head.text;
    case None => null
  }

  def title(c:Elem) = problemName(c) + " [" + problemCode(c) + "]"

  def buildMenu: Node => TreeItem[Control] = {
    case c: Elem if c.label == "menu" || c.label == "topic" =>
      val treeItem = new TreeItem(new Label(problemName(c)): Control)
      c.child.filter(n => n.getClass != classOf[Text]).foreach {
        n => treeItem.getChildren.add(buildMenu(n))
      }
      treeItem
    case c: Elem if c.label == "item" =>
      val hyp = new Hyperlink(title(c))
      hyp.setStyle(linkStyle)
      hyp.setOnAction(new EventHandler[ActionEvent] {
        override def handle(event: ActionEvent): Unit = {
          val clazz = Class.forName("rosalind."+problemCode(c)+"$")
          val cons = clazz.getDeclaredConstructors();
          cons(0).setAccessible(true)
          cons(0).newInstance() match {
            case v:HasView =>
              var replaceView = true
              if (!Main.solutionPane.getChildren.isEmpty) {
                val dynPane = Main.solutionPane.getChildren().get(0)
                dynPane match {
                  case v: VBox => v.getChildren().get(0) match {
                    case l: Label => if (l.getText.contains("["+problemCode(c)+"]")) replaceView = false
                  }
                }
              }
              if (replaceView) {
                Main.solutionPane.getChildren.clear()
                Main.solutionPane.getChildren.add(v.view(title(c)))
              }
            case c:Any =>
          }
        }
      })
      hyp.setOnMouseEntered(new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent): Unit = hyp.setStyle(linkStyle + highlight)
      })
      hyp.setOnMouseExited(new EventHandler[MouseEvent] {
        override def handle(event: MouseEvent): Unit = hyp.setStyle(linkStyle)
      })
      val treeItem = new TreeItem(hyp: Control)
      c.child.filter(n => n.getClass != classOf[Text]).foreach {
        n => treeItem.getChildren.add(buildMenu(n))
      }
      treeItem
    case c => new TreeItem(new Label("text encountered:" + c))
  }

  val menuXML = <menu name="Topics">
    <topic name="Alignment">
      <item class="HAMM" name="Counting Point Mutations"/>
      <item class="PDST" name="Creating a Distance Matrix"/>
      <item class="NEED" name="Pairwise Global Alignment"/>
      <item class="SUBO" name="Suboptimal Local Alignment"/>
      <item class="TRAN" name="Transitions and Transversions"/>
      <item class="CLUS" name="Global Multiple Alignment"/>
      <item class="EDIT" name="Edit Distance"/>
      <item class="EDTA" name="Edit Distance Alignment"/>
      <item class="CTEA" name="Counting Optimal Alignments"/>
      <item class="GLOB" name="Global Alignment with Scoring Matrix"/>
      <item class="GCON" name="Global Alignment with Constant Gap Penalty"/>
      <item class="LOCA" name="Local Alignment with Scoring Matrix"/>
      <item class="MGAP" name="Maximizing the Gap Symbols of an Optimal Alignment"/>
      <item class="MULT" name="Multiple Alignment"/>
      <item class="GAFF" name="Global Alignment with Scoring Matrix and Affine Gap Penalty"/>
      <item class="OAP" name="Overlap Alignment"/>
      <item class="SMGB" name="Semiglobal Alignment"/>
      <item class="LAFF" name="Local Alignment with Affine Gap Penalty"/>
      <item class="OSYM" name="Isolating Symbols in Alignments"/>
    </topic>
    <topic name="Combinatorics">
      <item class="FIB" name="Rabbits and Recurrence Relations"/>
      <item class="FIBD" name="Mortal Fibonacci Rabbits"/>
      <item class="MRNA" name="Inferring mRNA from Protein"/>
      <item class="PERM" name="Enumerating Gene Orders"/>
      <item class="SIGN" name="Enumerating Oriented Gene Orderings"/>
      <item class="SSET" name="Counting Subsets"/>
      <item class="ORF" name="Open Reading Frames"/>
      <item class="PMCH" name="Perfect Matchings and RNA Secondary Structures"/>
      <item class="PPER" name="Partial Permutations"/>
      <item class="INOD" name="Counting Phylogenetic Ancestors"/>
      <item class="CAT" name="Catalan Numbers and RNA Secondary Structures"/>
      <item class="MMCH" name="Maximum Matchings and RNA Secondary Structures"/>
      <item class="REAR" name="Reversal Distance"/>
      <item class="ASPC" name="Introduction to Alternative Splicing"/>
      <item class="MOTZ" name="Motzkin Numbers and RNA Secondary Structures"/>
      <item class="SORT" name="Sorting by Reversals"/>
      <item class="RNAS" name="Wobble Bonding and RNA Secondary Structures"/>
      <item class="CTEA" name="Counting Optimal Alignments"/>
      <item class="CUNR" name="Counting Unrooted Binary Trees"/>
      <item class="CNTQ" name="Counting Quartets"/>
      <item class="EUBT" name="Enumerating Unrooted Binary Trees"/>
      <item class="ROOT" name="Counting Rooted Binary Trees"/>
    </topic>
    <topic name="Computational Mass Spectrometry">
      <item class="PRTM" name="Calculating Protein Mass"/>
      <item class="SPEC" name="Inferring Protein from Spectrum"/>
      <item class="CONV" name="Comparing Spectra with the Spectral Convolution"/>
      <item class="FULL" name="Inferring Peptide from Full Spectrum"/>
      <item class="PRSM" name="Matching a Spectrum to a Protein"/>
      <item class="SGRA" name="Using the Spectrum Graph to Infer Peptides"/>
    </topic>
    <topic name="Divide and Conquer">
      <item class="BINS" name="Binary Search"/>
      <item class="MS" name="Merge Sort"/>
    </topic>
    <topic name="Dynamic Programming">
      <item class="FIB" name="Rabbits and Recurrence Relations"/>
      <item class="FIBD" name="Mortal Fibonacci Rabbits"/>
      <item class="LGIS" name="Longest Increasing Subsequence"/>
      <item class="PMCH" name="Perfect Matchings and RNA Secondary Structures"/>
      <item class="CAT" name="Catalan Numbers and RNA Secondary Structures"/>
      <item class="LCSQ" name="Finding a Shared Spliced Motif"/>
      <item class="MMCH" name="Maximum Matchings and RNA Secondary Structures"/>
      <item class="EDIT" name="Edit Distance"/>
      <item class="MOTZ" name="Motzkin Numbers and RNA Secondary Structures"/>
      <item class="SCSP" name="Interleaving Two Motifs"/>
      <item class="EDTA" name="Edit Distance Alignment"/>
      <item class="ITWV" name="Finding Disjoint Motifs in a Gene"/>
      <item class="RNAS" name="Wobble Bonding and RNA Secondary Structures"/>
      <item class="GLOB" name="Global Alignment with Scoring Matrix"/>
      <item class="GCON" name="Global Alignment with Constant Gap Penalty"/>
      <item class="LOCA" name="Local Alignment with Scoring Matrix"/>
      <item class="MGAP" name="Maximizing the Gap Symbols of an Optimal Alignment"/>
      <item class="MULT" name="Multiple Alignment"/>
      <item class="GAFF" name="Global Alignment with Scoring Matrix and Affine Gap Penalty"/>
      <item class="OAP" name="Overlap Alignment"/>
      <item class="SMGB" name="Semiglobal Alignment"/>
      <item class="LAFF" name="Local Alignment with Affine Gap Penalty"/>
      <item class="OSYM" name="Isolating Symbols in Alignments"/>
    </topic>
    <topic name="Genome Assembly">
      <item class="LONG" name="Genome Assembly as Shortest Superstring"/>
      <item class="CORR" name="Error Correction in Reads"/>
      <item class="DBRU" name="Constructing a De Bruijn Graph"/>
      <item class="PCOV" name="Genome Assembly with Perfect Coverage"/>
      <item class="GASM" name="Genome Assembly Using Reads"/>
      <item class="ASMQ" name="Assessing Assembly Quality with N50 and N75"/>
      <item class="GREP" name="Genome Assembly with Perfect Coverage and Repeats"/>
    </topic>
    <topic name="Genome Rearrangement">
      <item class="PERM" name="Enumerating Gene Orders"/>
      <item class="SIGN" name="Enumerating Oriented Gene Orderings"/>
      <item class="LGIS" name="Longest Increasing Subsequence"/>
      <item class="PPER" name="Partial Permutations"/>
      <item class="REAR" name="Reversal Distance"/>
      <item class="SORT" name="Sorting by Reversals"/>
    </topic>
    <topic name="Graph Algorithms">
      <item class="GRPH" name="Overlap Graphs"/>
      <item class="TREE" name="Completing a Tree"/>
      <item class="TRIE" name="Introduction to Pattern Matching"/>
      <item class="LREP" name="Finding the Longest Multiple Repeat"/>
      <item class="RNAS" name="Wobble Bonding and RNA Secondary Structures"/>
      <item class="PCOV" name="Genome Assembly with Perfect Coverage"/>
      <item class="SGRA" name="Using the Spectrum Graph to Infer Peptides"/>
      <item class="SUFF" name="Encoding Suffix Trees"/>
      <item class="GASM" name="Genome Assembly Using Reads"/>
      <item class="MREP" name="Identifying Maximal Repeats"/>
      <item class="GREP" name="Genome Assembly with Perfect Coverage and Repeats"/>
    </topic>
    <topic name="Graphs">
      <item class="DEG" name="Degree Array"/>
      <item class="DDEG" name="Double-Degree Array"/>
      <item class="BFS" name="Breadth-First Search"/>
      <item class="CC" name="Connected Components"/>
      <item class="DIJ" name="Dijkstra's Algorithm"/>
      <item class="BIP" name="Testing Bipartiteness"/>
      <item class="DAG" name="Testing Acyclicity"/>
      <item class="SQ" name="Square in a Graph"/>
      <item class="BF" name="Bellman-Ford Algorithm"/>
      <item class="CTE" name="Shortest Cycle Through a Given Edge"/>
      <item class="TS" name="Topological Sorting"/>
      <item class="HDAG" name="Hamiltonian Path in DAG"/>
      <item class="NWC" name="Negative Weight Cycle"/>
      <item class="SCC" name="Strongly Connected Components"/>
      <item class="2SAT" name="2-Satisfiability"/>
      <item class="GS" name="General Sink"/>
      <item class="SC" name="Semi-Connected Graph"/>
      <item class="SDAG" name="Shortest Paths in DAG"/>
    </topic>
    <topic name="Heredity">
      <item class="IPRB" name="Mendel's First Law"/>
      <item class="IEV" name="Calculating Expected Offspring"/>
      <item class="LIA" name="Independent Alleles"/>
      <item class="INDC" name="Independent Segregation of Chromosomes"/>
      <item class="MEND" name="Inferring Genotype from a Pedigree"/>
      <item class="SEXL" name="Sex-Linked Inheritance"/>
    </topic>
    <topic name="Phylogeny">
      <item class="TREE" name="Completing a Tree"/>
      <item class="INOD" name="Counting Phylogenetic Ancestors"/>
      <item class="PDST" name="Creating a Distance Matrix"/>
      <item class="NWCK" name="Distances in Trees"/>
      <item class="CTBL" name="Creating a Character Table"/>
      <item class="NKEW" name="Newick Format with Edge Weights"/>
      <item class="CSTR" name="Creating a Character Table from Genetic Strings"/>
      <item class="CUNR" name="Counting Unrooted Binary Trees"/>
      <item class="QRT" name="Quartets"/>
      <item class="CHBP" name="Character-Based Phylogeny"/>
      <item class="CNTQ" name="Counting Quartets"/>
      <item class="EUBT" name="Enumerating Unrooted Binary Trees"/>
      <item class="MEND" name="Inferring Genotype from a Pedigree"/>
      <item class="ROOT" name="Counting Rooted Binary Trees"/>
      <item class="SPTD" name="Phylogeny Comparison with Split Distance"/>
      <item class="ALPH" name="Alignment-Based Phylogeny"/>
      <item class="CSET" name="Fixing an Inconsistent Character Set"/>
      <item class="QRTD" name="Quartet Distance"/>
      <item class="RSUB" name="Identifying Reversing Substitutions"/>
    </topic>
    <topic name="Population Dynamics">
      <item class="AFRQ" name="Counting Disease Carriers"/>
      <item class="WFMD" name="The Wright-Fisher Model of Genetic Drift"/>
      <item class="FOUN" name="The Founder Effect and Genetic Drift"/>
    </topic>
    <topic name="Probability">
      <item class="IPRB" name="Mendel's First Law"/>
      <item class="IEV" name="Calculating Expected Offspring"/>
      <item class="LIA" name="Independent Alleles"/>
      <item class="PROB" name="Introduction to Random Strings"/>
      <item class="RSTR" name="Matching Random Motifs"/>
      <item class="EVAL" name="Expected Number of Restriction Sites"/>
      <item class="INDC" name="Independent Segregation of Chromosomes"/>
      <item class="AFRQ" name="Counting Disease Carriers"/>
      <item class="MEND" name="Inferring Genotype from a Pedigree"/>
      <item class="SEXL" name="Sex-Linked Inheritance"/>
      <item class="WFMD" name="The Wright-Fisher Model of Genetic Drift"/>
      <item class="EBIN" name="Wright-Fisher's Expected Behavior"/>
      <item class="FOUN" name="The Founder Effect and Genetic Drift"/>
    </topic>
    <topic name="Set Theory">
      <item class="SSET" name="Counting Subsets"/>
      <item class="SETO" name="Introduction to Set Operations"/>
      <item class="PDPL" name="Creating a Restriction Map"/>
    </topic>
    <topic name="Sorting">
      <item class="INS" name="Insertion Sort"/>
      <item class="MAJ" name="Majority Element"/>
      <item class="MER" name="Merge Two Sorted Arrays"/>
      <item class="2SUM" name="2SUM"/>
      <item class="HEA" name="Building a Heap"/>
      <item class="MS" name="Merge Sort"/>
      <item class="PAR" name="2-Way Partition"/>
      <item class="HS" name="Heap Sort"/>
      <item class="INV" name="Counting Inversions"/>
      <item class="3SUM" name="3SUM"/>
      <item class="PAR3" name="3-Way Partition"/>
      <item class="PS" name="Partial Sort"/>
      <item class="MED" name="Median"/>
      <item class="QS" name="Quick Sort"/>
    </topic>
    <topic name="String Algorithms">
      <item class="DNA" name="Counting DNA Nucleotides"/>
      <item class="RNA" name="Transcribing DNA into RNA"/>
      <item class="REVC" name="Complementing a Strand of DNA"/>
      <item class="GC" name="Computing GC Content"/>
      <item class="SUBS" name="Finding a Motif in DNA"/>
      <item class="CONS" name="Consensus and Profile"/>
      <item class="LCSM" name="Finding a Shared Motif"/>
      <item class="REVP" name="Locating Restriction Sites"/>
      <item class="SPLC" name="RNA Splicing"/>
      <item class="LEXF" name="Enumerating k-mers Lexicographically"/>
      <item class="PMCH" name="Perfect Matchings and RNA Secondary Structures"/>
      <item class="SSEQ" name="Finding a Spliced Motif"/>
      <item class="CAT" name="Catalan Numbers and RNA Secondary Structures"/>
      <item class="KMER" name="k-Mer Composition"/>
      <item class="KMP" name="Speeding Up Motif Finding"/>
      <item class="LCSQ" name="Finding a Shared Spliced Motif"/>
      <item class="LEXV" name="Ordering Strings of Varying Length Lexicographically"/>
      <item class="MMCH" name="Maximum Matchings and RNA Secondary Structures"/>
      <item class="MOTZ" name="Motzkin Numbers and RNA Secondary Structures"/>
      <item class="SCSP" name="Interleaving Two Motifs"/>
      <item class="TRIE" name="Introduction to Pattern Matching"/>
      <item class="ITWV" name="Finding Disjoint Motifs in a Gene"/>
      <item class="SUFF" name="Encoding Suffix Trees"/>
      <item class="LING" name="Linguistic Complexity of a Genome"/>
      <item class="MREP" name="Identifying Maximal Repeats"/>
      <item class="KSIM" name="Finding All Similar Motifs"/>
    </topic>
  </menu>

  val menu = buildMenu(menuXML)
}
