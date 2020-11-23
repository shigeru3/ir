import scala.collection.{SortedSet, mutable}

object Store {
	def storeDocumentIndex(documentIndex: DocumentIndex): DocumentIndex = {
		postingList.get(documentIndex.term).fold {
			postingList.put(documentIndex.term, documentIndex.docIds)
			documentIndex
		} { stored: SortedSet[Int] =>
			val docIds: SortedSet[Int] = stored ++ documentIndex.docIds
			this.postingList.update(documentIndex.term, docIds)
			documentIndex
		}
	}

	val postingList: scala.collection.mutable.Map[String, SortedSet[Int]] = scala.collection.mutable.Map()
}
