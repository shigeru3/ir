import models.{Document, DocumentIndex, DocumentPositionIndex, PositionIndex}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.SortedSet

class IndexerSpec extends AnyFlatSpec with should.Matchers {
	"Indexer.documentIndex" should "return documentIndices" in {
		val content = "Do you quarrel, sir?"
		val documentIndex: Document = Document(1, content)
		Indexer.documentIndex(documentIndex) should be(
			Seq(
				DocumentIndex("do", SortedSet(1)),
				DocumentIndex("you", SortedSet(1)),
				DocumentIndex("quarrel", SortedSet(1)),
				DocumentIndex("sir", SortedSet(1))
			)
		)
	}

	"Indexer.positionIndex" should "return positionIndices" in {
		val content = "Do you quarrel, sir?"
		val document: Document = Document(1, content)

		Indexer.positionIndex(document) should be(
			Seq(
				PositionIndex("do", Set(DocumentPositionIndex(1, SortedSet(1)))),
				PositionIndex("you", Set(DocumentPositionIndex(1, SortedSet(2)))),
				PositionIndex("sir", Set(DocumentPositionIndex(1, SortedSet(4)))),
				PositionIndex("quarrel", Set(DocumentPositionIndex(1, SortedSet(3))))
			)
		)
	}
}
