public class ParsingTable {
	public enum Rules {
		//NONTERMINAL
		CLASS(false, null), MAIN(false, null), BODY(false, null), BODY_EMPTY(false, null), 
		STMT(false, null), OUT_PRINTLN_BODY(false, null), INIT_STMT(false, null), 
		ASSIGNREF_STMT(false, null), ASSIGN_REF(false, null), IF_STMT(false, null), 
		ELSE_STMT(false, null), ELSE_BODY(false, null), WHILE_LOOP(false, null), 
		FOR_LOOP(false, null), CONDITION(false, null), CONDITIONAL_OP(false, null), 
		EXP(false, null), EXP_(false, null), TERM(false, null), TERM_(false, null), 
		ADD_OP(false, null), MULDIV_OP(false, null), FACTOR(false, null),
		
		// TERMINAL
		_CLASS(true, Token.TokenType.KEYWORD), _ID(true, Token.TokenType.ID), 
		_LEFT_CURLY(true, Token.TokenType.LEFT_CURLY_BRACE), _RIGHT_CURLY(true, Token.TokenType.RIGHT_CURLY_BRACE), 
		_MAIN(true, Token.TokenType.KEYWORD), _LEFT_PAREN(true, Token.TokenType.LEFT_PARENTHESIS), 
		_RIGHT_PAREN(true, Token.TokenType.RIGHT_PARENTHESIS), _OUT_PRINTLN(true, Token.TokenType.KEYWORD), 
		_INT(true, Token.TokenType.KEYWORD), _ASSIGN(true, Token.TokenType.ASSIGNMENT), 
		_TERMINAL(true, Token.TokenType.TERMINATOR), _STRING(true, Token.TokenType.STRING_LITERAL), 
		_INCREASE(true, Token.TokenType.INCREMENT), _IF(true, Token.TokenType.KEYWORD), _ELSE(true, Token.TokenType.KEYWORD), 
		_WHILE(true, Token.TokenType.KEYWORD), _FOR(true, Token.TokenType.KEYWORD), _NUMBER(true, Token.TokenType.NUMBER_LITERAL), 
		_LT(true, Token.TokenType.LESS_THAN), _BT(true, Token.TokenType.GREATER_THAN), _LAE(true, Token.TokenType.LTE), 
		_BAE(true, Token.TokenType.GTE), _EQUAL(true, Token.TokenType.EQUAL), _NONE(true, Token.TokenType.NEQ), 
		_PLUS(true, Token.TokenType.PLUS), _MINUS(true, Token.TokenType.MINUS), _TIMES(true, Token.TokenType.TIMES), 
		_DIVIDE(true,Token.TokenType.DIVIDE), _EPSILON(true, null), _DOLLAR(true, null);
		
		Rules[] _class, _id, _left_curly, _right_curly, _main, _right_paren, _left_paren, 
		_out_println, _int, _assign, _terminal, _string, _increase, 
		_if, _else, _while, _for, _number, _lt, _bt, _lae, _bae, _equal, _none,
		_plus, _minus, _times, _divide;
		
		static {
			CLASS._class = new Rules[] {_CLASS, _ID, _LEFT_CURLY, MAIN, _RIGHT_CURLY};
			MAIN._main = new Rules[] {_MAIN, _LEFT_PAREN, _RIGHT_PAREN, _LEFT_CURLY, BODY , _RIGHT_CURLY};
			
			BODY._id = new Rules[] {STMT, BODY_EMPTY};
			BODY._right_curly = new Rules[] {_EPSILON};
			BODY._out_println = new Rules[] {STMT, BODY_EMPTY};
			BODY._int = new Rules[] {STMT, BODY_EMPTY};
			BODY._if = new Rules[] {STMT, BODY_EMPTY};
			BODY._while = new Rules[] {STMT, BODY_EMPTY};
			BODY._for = new Rules[] {STMT, BODY_EMPTY};
			
			BODY_EMPTY._id = new Rules[] {BODY};
			BODY_EMPTY._right_curly = new Rules[] {_EPSILON};
			BODY_EMPTY._out_println = new Rules[] {BODY};
			BODY_EMPTY._int = new Rules[] {BODY};
			BODY_EMPTY._if = new Rules[] {BODY};
			BODY_EMPTY._while = new Rules[] {BODY};
			BODY_EMPTY._for = new Rules[] {BODY};
			
			STMT._id = new Rules[] {ASSIGNREF_STMT, _TERMINAL};
			STMT._out_println = new Rules[] {_OUT_PRINTLN, _LEFT_PAREN, OUT_PRINTLN_BODY, _TERMINAL};
			STMT._int = new Rules[] {INIT_STMT, _TERMINAL};
			STMT._if = new Rules[] {IF_STMT};
			STMT._while = new Rules[] {WHILE_LOOP};
			STMT._for = new Rules[] {FOR_LOOP};
			
			OUT_PRINTLN_BODY._id = new Rules[] {_ID, _RIGHT_PAREN};
			OUT_PRINTLN_BODY._string = new Rules[] {_STRING, _RIGHT_PAREN};
			
			INIT_STMT._int = new Rules[] {_INT, _ID, _ASSIGN, EXP};
			ASSIGNREF_STMT._id = new Rules[] {_ID, ASSIGN_REF};
			
			ASSIGN_REF._assign = new Rules[] {_ASSIGN, EXP};
			ASSIGN_REF._increase = new Rules[] {_INCREASE};
			
			IF_STMT._if = new Rules[] {_IF, _LEFT_PAREN, CONDITION, _RIGHT_PAREN, 
					_LEFT_CURLY, BODY, _RIGHT_CURLY, ELSE_STMT};
			
			ELSE_STMT._id = new Rules[] {_EPSILON};
			ELSE_STMT._right_curly = new Rules[] {_EPSILON};
			ELSE_STMT._out_println = new Rules[] {_EPSILON};
			ELSE_STMT._int = new Rules[] {_EPSILON};
			ELSE_STMT._if = new Rules[] {_EPSILON};
			ELSE_STMT._else = new Rules[] {_ELSE, ELSE_BODY};
			ELSE_STMT._while = new Rules[] {_EPSILON};
			ELSE_STMT._for = new Rules[] {_EPSILON};
			
			ELSE_BODY._left_curly = new Rules[] {_LEFT_CURLY, BODY, _RIGHT_CURLY};
			ELSE_BODY._if = new Rules[] {IF_STMT};
			
			WHILE_LOOP._while = new Rules[] {_WHILE, _LEFT_PAREN, CONDITION, _RIGHT_PAREN, 
					_LEFT_CURLY, BODY, _RIGHT_CURLY};
			FOR_LOOP._for = new Rules[] {_FOR, _LEFT_PAREN, INIT_STMT, _TERMINAL, CONDITION, _TERMINAL, 
					ASSIGNREF_STMT, _RIGHT_PAREN, _LEFT_CURLY, BODY, _RIGHT_CURLY};
			
			CONDITION._id = new Rules[] {EXP, CONDITIONAL_OP, EXP};
			CONDITION._left_paren = new Rules[] {EXP, CONDITIONAL_OP, EXP};
			CONDITION._number = new Rules[] {EXP, CONDITIONAL_OP, EXP};
			
			CONDITIONAL_OP._lt = new Rules[] {_LT};
			CONDITIONAL_OP._bt = new Rules[] {_BT};
			CONDITIONAL_OP._lae = new Rules[] {_LAE};
			CONDITIONAL_OP._bae = new Rules[] {_BAE};
			CONDITIONAL_OP._equal = new Rules[] {_EQUAL};
			CONDITIONAL_OP._none = new Rules[] {_NONE};
			
			EXP._id = new Rules[] {TERM, EXP_};
			EXP._left_paren = new Rules[] {TERM, EXP_};
			EXP._number = new Rules[] {TERM, EXP_};
			
			EXP_._right_paren = new Rules[] {_EPSILON};
			EXP_._terminal = new Rules[] {_EPSILON};
			EXP_._lt = new Rules[] {_EPSILON};
			EXP_._bt = new Rules[] {_EPSILON};
			EXP_._lae = new Rules[] {_EPSILON};
			EXP_._bae = new Rules[] {_EPSILON};
			EXP_._equal = new Rules[] {_EPSILON};
			EXP_._none = new Rules[] {_EPSILON};
			EXP_._plus = new Rules[] {ADD_OP, TERM, EXP_};
			EXP_._minus = new Rules[] {ADD_OP, TERM, EXP_};
			
			TERM._id = new Rules[] {FACTOR, TERM_};
			TERM._left_paren = new Rules[] {FACTOR, TERM_};
			TERM._number = new Rules[] {FACTOR, TERM_};
			
			TERM_._right_paren = new Rules[] {_EPSILON};
			TERM_._terminal = new Rules[] {_EPSILON};
			TERM_._lt = new Rules[] {_EPSILON};
			TERM_._bt = new Rules[] {_EPSILON};
			TERM_._lae = new Rules[] {_EPSILON};
			TERM_._bae = new Rules[] {_EPSILON};
			TERM_._equal = new Rules[] {_EPSILON};
			TERM_._none = new Rules[] {_EPSILON};
			TERM_._plus = new Rules[] {_EPSILON};
			TERM_._minus = new Rules[] {_EPSILON};
			TERM_._times = new Rules[] {MULDIV_OP, FACTOR, TERM_};
			TERM_._divide = new Rules[] {MULDIV_OP, FACTOR, TERM_};
			
			ADD_OP._plus = new Rules[] {_PLUS};
			ADD_OP._minus = new Rules[] {_MINUS};
			
			MULDIV_OP._times = new Rules[] {_TIMES};
			MULDIV_OP._divide = new Rules[] {_DIVIDE};
			
			FACTOR._id = new Rules[] {_ID};
			FACTOR._left_paren = new Rules[] {_LEFT_PAREN, EXP, _RIGHT_PAREN};
			FACTOR._number = new Rules[] {_NUMBER};
		}
		
		private boolean terminal;
		private Token.TokenType value;
		
		Rules(boolean terminal, Token.TokenType value) {
			this.terminal = terminal;
			this.value = value;
		}
		
		public Rules[] transition(Token.TokenType tt, String value) {
			switch(tt) {
				case KEYWORD:
					if(value == "class") return this._class;
					else if(value == "int") return this._int;
					else if(value == "main") return this._main;
					else if(value == "out.println") return this._out_println;
					else if(value == "if") return this._if;
					else if(value == "else") return this._else;
					else if(value == "for") return this._for;
					else if(value == "while") return this._while;
					else return null;
				case ID:
					return this._id;
				case LEFT_CURLY_BRACE:
					return this._left_curly;
				case RIGHT_CURLY_BRACE:
					return this._right_curly;
				case LEFT_PARENTHESIS:
					return this._left_paren;
				case RIGHT_PARENTHESIS:
					return this._right_paren;
				case ASSIGNMENT:
					return this._assign;
				case TERMINATOR:
					return this._terminal;
				case STRING_LITERAL:
					return this._string;
				case INCREMENT:
					return this._increase;
				case NUMBER_LITERAL:
					return this._number;
				case LESS_THAN:
					return this._lt;
				case GREATER_THAN:
					return this._bt;
				case LTE:
					return this._lae;
				case GTE:
					return this._bae;
				case EQUAL:
					return this._equal;
				case NEQ:
					return this._none;
				case PLUS:
					return this._plus;
				case MINUS:
					return this._minus;
				case TIMES:
					return this._times;
				case DIVIDE:
					return this._divide;
	    		default:
	    			return null;
			}
			
		}
		
		public boolean getTerminal() {
			return terminal;
		}
		
		public Token.TokenType getValue() {
			return value;
		}
	}
}
