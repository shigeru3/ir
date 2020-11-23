import scala.collection.SortedSet

case class DocumentIndex(
												term: String,
												docIds: SortedSet[Int]
												) {
	def length: Int = docIds.size
}
