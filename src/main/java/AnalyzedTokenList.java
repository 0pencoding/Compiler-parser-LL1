import java.util.ArrayList;

@SuppressWarnings("serial")
public class AnalyzedTokenList extends ArrayList<Token> {
	private int idx = 0;
	
	public Token.TokenType getCurrentType() {
		return this.get(idx).getTokenType();
	}
	
	public String getCurrentValue() {
		return this.get(idx).getValue();
	}
	
	public boolean next() {
		if (this.size() > idx + 1) {
			idx++;
			return true;
		} else return false;
	}
	
	public boolean is_last() {
		return (idx == this.size() - 1 && this.getCurrentType() == Token.TokenType.DOLLAR);
	}
	
	public void print_token() {
		System.out.println(this.get(idx).toString());
	}
	
	public void print_tokens() {
		for(Token t : this) {
			System.out.println(t.toString());
		}
	}
	
	public void print_tokens_line() {
		System.out.print("IS: ");
		for(int i = idx; i < this.size(); i++)
			System.out.print(this.get(i).toString());
		System.out.println();
	}
}
