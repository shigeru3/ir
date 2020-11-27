import models.{Document, DocumentIndex, PositionIndex}

object MultiIndexer {
	def documentIndex(documents: Seq[Document]): Seq[Seq[DocumentIndex]] = {
		documents.map { document =>
			Indexer.documentIndex(document)
		}
	}

	def positionIndex(documents: Seq[Document]): Seq[Seq[PositionIndex]] = {
		documents.map { document =>
			Indexer.positionIndex(document)
		}
	}
}

