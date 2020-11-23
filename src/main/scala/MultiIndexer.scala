import models.Document

object MultiIndexer {
	def documentIndex(documents: Seq[Document]): Seq[Seq[DocumentIndex]] = {
		documents.map { document =>
			Indexer.documentIndex(document)
		}
	}
}

