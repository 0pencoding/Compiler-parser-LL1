@SuppressWarnings("serial")
public class ParserException extends Exception {
	private String fileName; // 오류 프로젝트
	
	public ParserException() {
		this.fileName = null;
	}

	public ParserException(String fileName) {
		this.fileName = fileName;
	}
	
	public String getMessage() {
		String msg = "exception.ParserException:";
		
		return msg + "\n\t\" + fileName + \": An error occurred in LL(1) parser\n";
	}
}
