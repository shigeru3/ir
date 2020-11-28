package fixtures

import models.Document

trait Documents {
	val document1: Document = Document(1, "Do you quarrel, sir?")
	val document2: Document = Document(2, "Quarrel sir! no, sir!")
	val document3: Document = Document(3, "If you do, sir, I am for you: I serve as good a man as you.")
	val document4: Document = Document(4, "No better.")
	val document5: Document = Document(5, "Well, sir.")
	val documents: Seq[Document] = Seq(document1, document2, document3, document4, document5)
}
