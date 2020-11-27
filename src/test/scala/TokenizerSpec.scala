import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class TokenizerSpec extends AnyFlatSpec with should.Matchers {
	"Tokenizer" should "tokenize" in {
		val content = "Do you quarrel, sir?"
		Tokenizer.tokenize(content) should be (Seq("do", "you", "quarrel", "sir"))
	}

	"Tokenizer" should "tokenize with position" in {
		val content = "Do you quarrel, sir?"
		Tokenizer.tokenizeWithPosition(content) should be (Map(1 -> "do", 2 -> "you", 3 -> "quarrel", 4 -> "sir"))
	}
}
