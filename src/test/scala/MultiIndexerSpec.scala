import models.{Document, DocumentIndex, DocumentPositionIndex}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.SortedSet

class MultiIndexerSpec extends AnyFlatSpec with should.Matchers {

	val document1: Document = Document(1, "Do you quarrel, sir?")
	val document2: Document = Document(2, "Quarrel sir! no, sir!")

	"MultiIndexer.documentIndex" should "return documentIndices" in {
		MultiIndexer.documentIndex(Seq(document1, document2)) should be (
			Seq(
				Seq(
					models.DocumentIndex("do", SortedSet(1)),
					models.DocumentIndex("you", SortedSet(1)),
					models.DocumentIndex("quarrel", SortedSet(1)),
					models.DocumentIndex("sir", SortedSet(1))
				),
				Seq(
					DocumentIndex("quarrel", SortedSet(2)),
					models.DocumentIndex("sir", SortedSet(2)),
					models.DocumentIndex("no", SortedSet(2)),
					models.DocumentIndex("sir", SortedSet(2))
				)
			)
		)
	}

	"MultiIndexer.positionIndex" should "return positionIndices" in {
		MultiIndexer.positionIndex(Seq(document1, document2)) should be (
			Seq(
				Seq(
					models.PositionIndex("do", Set(DocumentPositionIndex(1, SortedSet(1)))),
					models.PositionIndex("you", Set(DocumentPositionIndex(1, SortedSet(2)))),
					models.PositionIndex("sir", Set(DocumentPositionIndex(1, SortedSet(4)))),
					models.PositionIndex("quarrel", Set(DocumentPositionIndex(1, SortedSet(3))))
				),
				Seq(
					models.PositionIndex("sir", Set(DocumentPositionIndex(2, SortedSet(2, 4)))),
					models.PositionIndex("quarrel", Set(DocumentPositionIndex(2, SortedSet(1)))),
					models.PositionIndex("no", Set(DocumentPositionIndex(2, SortedSet(3))))
				)
			)
		)
	}
}
