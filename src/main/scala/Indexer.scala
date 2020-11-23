import models.Document

import scala.collection.SortedSet

object Indexer {
	def documentIndex(document: Document): Seq[DocumentIndex] = {
		Tokenizer.tokenize(document.content).map { term =>
			Store.storeDocumentIndex(
				DocumentIndex(term, SortedSet(document.docId))
			)
		}
	}
}
