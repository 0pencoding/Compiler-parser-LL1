import java.util.Stack;

public class ParsingStack extends Stack<ParsingTable.Rules> {
	public boolean is_last() {
		return (this.peek() == ParsingTable.Rules._DOLLAR);
	}
	
	public boolean is_T() {
		return this.peek().getTerminal();
	}
	
	public Token.TokenType getValue() {
		return this.peek().getValue();
	}
	
	public ParsingTable.Rules[] getEntry(Token.TokenType tt, String value) {
		return this.peek().transition(tt, value);
	}
	
	public void print_ps() {
		System.out.print("PS: ");
		for(ParsingTable.Rules rules : this)
			System.out.print(rules + " ");
		System.out.println();
	}
}
