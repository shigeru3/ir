import models.Document
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.SortedSet

class MultiIndexerSpec extends AnyFlatSpec with should.Matchers {
	"MultiIndexer.documentIndex" should "return documentIndices" in {
		val document1: Document = Document(1, "Do you quarrel, sir?")
		val document2: Document = Document(2, "Quarrel sir! no, sir!")

		MultiIndexer.documentIndex(Seq(document1, document2)) should be (
			Seq(
				Seq(
					DocumentIndex("do", SortedSet(1)),
					DocumentIndex("you", SortedSet(1)),
					DocumentIndex("quarrel", SortedSet(1)),
					DocumentIndex("sir", SortedSet(1))
				),
				Seq(
					DocumentIndex("quarrel", SortedSet(2)),
					DocumentIndex("sir", SortedSet(2)),
					DocumentIndex("no", SortedSet(2)),
					DocumentIndex("sir", SortedSet(2))
				)
			)
		)
	}
}
