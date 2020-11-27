package models

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.SortedSet

class DocumentIndexSpec extends AnyFlatSpec with should.Matchers {
	"models.DocumentIndex.length" should "return index size" in {
		val documentIndex = DocumentIndex(
			term = "a",
			docIds = SortedSet(3)
		)
		documentIndex.length should be(1)
	}
}
