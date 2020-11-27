import models.{Document, DocumentIndex, DocumentPositionIndex, PositionIndex}

import scala.collection.SortedSet

object Indexer {
	def documentIndex(document: Document): Seq[DocumentIndex] = {
		Tokenizer.tokenize(document.content).map { term =>
			Store.storeDocumentIndex(
				DocumentIndex(term, SortedSet(document.docId))
			)
		}
	}

	def positionIndex(document: Document): Seq[PositionIndex] = {
		Tokenizer.tokenizeWithPosition(document.content).groupBy(_._2).map { case (term, positions) =>
			PositionIndex(term, Set(DocumentPositionIndex(document.docId, scala.collection.SortedSet[Int]() ++ positions.keys.toSet)))
		}.toSeq.map(Store.storePositionIndex)
	}
}
