object Tokenizer {
	def tokenize(content: String): Seq[String] = {
		content.split(" ").map(_.toLowerCase().replaceAll("[!?,.:]", ""))
	}

	def tokenizeWithPosition(content: String): Map[Int, String] = {
		val numberStream = LazyList.from(1).iterator
		tokenize(content).map {
			(numberStream.next(), _)
		}.toMap
	}
}
