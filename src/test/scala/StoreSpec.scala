import models.Document
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.collection.{SortedSet, mutable}

class StoreSpec extends AnyFlatSpec with should.Matchers {

	val document1: Document = Document(1, "Do you quarrel, sir?")
	val document2: Document = Document(2, "Quarrel sir! no, sir!")
	val document3: Document = Document(3, "If you do, sir, I am for you: I serve as good a man as you.")
	val document4: Document = Document(4, "No better.")
	val document5: Document = Document(5, "Well, sir.")
	val documents: Seq[Document] = Seq(document1, document2, document3, document4, document5)

	"Store.storeDocumentIndex" should "store documentNumberIndex" in {
		MultiIndexer.documentIndex(documents)

		Store.postingList.get("a") should be (Some(Set(3)))
		Store.postingList.get("am") should be (Some(Set(3)))
		Store.postingList.get("as") should be (Some(Set(3)))
		Store.postingList.get("better") should be (Some(Set(4)))
		Store.postingList.get("do") should be (Some(Set(1, 3)))
		Store.postingList.get("for") should be (Some(Set(3)))
		Store.postingList.get("good") should be (Some(Set(3)))
		Store.postingList.get("i") should be (Some(Set(3)))
		Store.postingList.get("if") should be (Some(Set(3)))
		Store.postingList.get("man") should be (Some(Set(3)))
		Store.postingList.get("no") should be (Some(Set(2, 4)))
		Store.postingList.get("quarrel") should be (Some(Set(1, 2)))
		Store.postingList.get("serve") should be (Some(Set(3)))
		Store.postingList.get("sir") should be (Some(Set(1, 2, 3, 5)))
		Store.postingList.get("well") should be (Some(Set(5)))
		Store.postingList.get("you") should be (Some(Set(1, 3)))
	}

	"Store.storePositionIndex" should "store positionIndex" in {
		MultiIndexer.positionIndex(documents)
		val indices = Store.positionIndices
		implicit val ordering: Ordering[(Int, SortedSet[Int])] = Ordering.by(_._1)
		Store.positionIndices.get("a") should be (Some(SortedSet((3, SortedSet(13)))))
		Store.positionIndices.get("am") should be (Some(SortedSet((3, SortedSet(6)))))
		Store.positionIndices.get("as") should be (Some(SortedSet((3, SortedSet(11, 15)))))
		Store.positionIndices.get("better") should be (Some(SortedSet((4, SortedSet(2)))))
		Store.positionIndices.get("do") should be (Some(SortedSet((1, SortedSet(1)), (3, SortedSet(3)))))
		Store.positionIndices.get("for") should be (Some(SortedSet((3, SortedSet(7)))))
		Store.positionIndices.get("good") should be (Some(SortedSet((3, SortedSet(12)))))
		Store.positionIndices.get("i") should be (Some(SortedSet((3, SortedSet(5, 9)))))
		Store.positionIndices.get("if") should be (Some(SortedSet((3, SortedSet(1)))))
		Store.positionIndices.get("man") should be (Some(SortedSet((3, SortedSet(14)))))
		Store.positionIndices.get("no") should be (Some(SortedSet((2, SortedSet(3)), (4, SortedSet(1)))))
		Store.positionIndices.get("quarrel") should be (Some(SortedSet((1, SortedSet(3)), (2, SortedSet(1)))))
		Store.positionIndices.get("serve") should be (Some(SortedSet((3, SortedSet(10)))))
		Store.positionIndices.get("sir") should be (Some(SortedSet((1, SortedSet(4)), (2, SortedSet(2, 4)), (3, SortedSet(4)), (5, SortedSet(1)))))
		Store.positionIndices.get("you") should be (Some(SortedSet((1, SortedSet(2)), (3, SortedSet(2, 8, 16)))))
	}
}
