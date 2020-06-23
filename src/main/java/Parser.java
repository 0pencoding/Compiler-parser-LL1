public class Parser {
	private AnalyzedTokenList is = new AnalyzedTokenList();
	private ParsingStack ps = new ParsingStack();
	private Scanner scanner = new Scanner();
	private ParserWriter writer;
	private String fileName;
	
	public void run(String[] args) {
		runScanner(args);
		
		parser_writer_ready();
		parse_ready();
		parse();
		
		if(ps.is_last() && is.is_last()) {
			System.out.println("Parsing OK");
			writer.write("Parsing OK");
			writer.close();
		} else {
			// TODO: ERROR
		}
	}
	
	private void runScanner(String[] args) {
		scanner.run(args);
		is = scanner.getTokenList(false);
		fileName = scanner.getFileName();
	}
	
	private void parser_writer_ready() {
		writer = new ParserWriter(fileName);
		writer.init();
	}
	
	private void parse_ready() {
		ps.push(new Token(Token.TokenType.DOLLAR, "$", -1));
		is.add(new Token(Token.TokenType.DOLLAR, "$", -1));
	}
	
	private void parse() {
		do {
//			if()
		} while(!ps.is_last() && !is.is_last());
	}
}
