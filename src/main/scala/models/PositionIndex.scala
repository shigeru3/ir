package models

import scala.collection.SortedSet


case class PositionIndex(
												term: String,
												docIds: Set[DocumentPositionIndex]
												) {
	def length: Int = docIds.size
}

case class DocumentPositionIndex(
																	docId: Int,
																	positions: SortedSet[Int]
																) {
	def length: Int = positions.size
}