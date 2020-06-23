import java.util.Stack;

public class ParsingStack extends Stack<Token> {
	public boolean is_last() {
		return (this.peek().getTokenType() == Token.TokenType.DOLLAR);
	}
}
