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
			System.out.println("Parsing Error");
			writer.write("Parsing Error");
			try {
				throw new ParserException(fileName);
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				writer.close();
			}
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
		ps.push(ParsingTable.Rules._DOLLAR);
		ps.push(ParsingTable.Rules.CLASS);
		is.add(new Token(Token.TokenType.DOLLAR, "$", -1));
	}
	
	private void parse() {
		do {
			if(ps.peek() == ParsingTable.Rules._EPSILON) {
				ps.pop();
			} else if(ps.is_T() && ps.getValue() == is.getCurrentType()) {
				ps.pop();
				is.next();
				is.print_token();
				writer.write(is.getCurrentValue() + "\t" + is.getCurrentType());
			} else if (!ps.is_T()){
				ParsingTable.Rules[] derivs = ps.getEntry(is.getCurrentType(), is.getCurrentValue());
				
				if(derivs == null) {
					System.out.println("Parsing Error");
					writer.write("Parsing Error");
					try {
						throw new ParserException(fileName);
					} catch (ParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						writer.close();
					}
					return;
				} else {
					ps.pop();
					for(int i = derivs.length - 1; i >= 0; i--) {
						ps.push(derivs[i]);
					}
				}
			} else {
				System.out.println("Parsing Error");
				writer.write("Parsing Error");
				try {
					throw new ParserException(fileName);
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					writer.close();
				}
				return;
			}
		} while(!ps.is_last() && !is.is_last());
	}
}
