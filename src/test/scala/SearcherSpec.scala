import fixtures.Documents
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class SearcherSpec extends AnyFlatSpec with should.Matchers with Documents {
	"Searcher.first" should "return first document id and position" in {
		MultiIndexer.positionIndex(documents)

		Searcher.first("a") should be (Some(3, 13))
		Searcher.last("sir") should be (Some(5, 2))
		Searcher.last("you") should be (Some(3, 16))

		Searcher.next("sir", (2, 2)) should be (Some(2, 4))
		Searcher.next("quarrel", (1, 3)) should be (Some(2, 1))
		Searcher.next("quarrel", (2, 1)) should be (None)
	}
}
