import models.Document
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.mutable

class StoreSpec extends AnyFlatSpec with should.Matchers {
	"Store.storeDocumentIndex" should "store documentNumberIndex" in {
		val document1: Document = Document(1, "Do you quarrel, sir?")
		val document2: Document = Document(2, "Quarrel sir! no, sir!")

		MultiIndexer.documentIndex(Seq(document1, document2))

		Store.postingList.get("do") should be (Some(Set(1)))
		Store.postingList.get("you") should be (Some(Set(1)))
		Store.postingList.get("quarrel") should be (Some(Set(1, 2)))
		Store.postingList.get("sir") should be (Some(Set(1, 2)))
		Store.postingList.get("no") should be (Some(Set(2)))
	}
}
