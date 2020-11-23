object Tokenizer {
	def tokenize(content: String): Seq[String] = {
		content.split(" ").map(_.toLowerCase().replaceAll("[!?,.:]", ""))
	}
}
