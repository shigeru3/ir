import models.{DocumentIndex, PositionIndex}

import scala.collection.SortedSet
import scala.math.Ordering.Implicits.sortedSetOrdering

object Store {
	def storeDocumentIndex(documentIndex: DocumentIndex): DocumentIndex = {
		postingList.get(documentIndex.term).fold {
			postingList.put(documentIndex.term, documentIndex.docIds)
			documentIndex
		} { stored: SortedSet[Int] =>
			val docIds: SortedSet[Int] = stored ++ documentIndex.docIds
			postingList.update(documentIndex.term, docIds)
			documentIndex
		}
	}

	def storePositionIndex(positionIndex: PositionIndex): PositionIndex = {
		positionIndices.get(positionIndex.term).fold {
			val docIds = positionIndex.docIds.map { documentPositionIndex =>
				documentPositionIndex.docId -> documentPositionIndex.positions
			}
			positionIndices.put(positionIndex.term, SortedSet[(Int, SortedSet[Int])]() ++ docIds)
			positionIndex
		} { stored: SortedSet[(Int, SortedSet[Int])] =>
			val positions = positionIndex.docIds.map(a => (a.docId, a.positions))
			val docIds: SortedSet[(Int, SortedSet[Int])] = stored ++ positions
			positionIndices.update(positionIndex.term, docIds)
			positionIndex
		}
	}

	type documentIndexPostingList = scala.collection.mutable.Map[String, SortedSet[Int]]
	val postingList: documentIndexPostingList =  scala.collection.mutable.Map()

	// Map(term => Set(Map(docId => Set(position)))
	val positionIndices: scala.collection.mutable.Map[String, SortedSet[(Int, SortedSet[Int])]] = scala.collection.mutable.Map()
}
