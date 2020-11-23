import models.Document
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
}
