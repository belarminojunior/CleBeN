/* SimpleLexer.flex */

%{
/* Java code that is included in the lexer class */

package lexer;

import java.io.IOException;

%}

%%

/* Declare the states */
%state COMMENT

/* Define the macros */
DIGIT = [0-9]
LETTER = [a-zA-Z]
WHITESPACE = [ \t\n\r]

%%

/* Rules and actions */

/* Ignore whitespace */
{WHITESPACE} { /* Ignore whitespace */ }

/* Keywords */
"if"       { System.out.println("IF"); return Token.IF; }
"else"     { System.out.println("ELSE"); return Token.ELSE; }
"while"    { System.out.println("WHILE"); return Token.WHILE; }

/* Identifiers */
{LETTER}({LETTER}|{DIGIT})* { System.out.println("IDENTIFIER(" + yytext() + ")"); return Token.IDENTIFIER; }

/* Numbers */
{DIGIT}+  { System.out.println("NUMBER(" + yytext() + ")"); return Token.NUMBER; }

/* Operators */
"+"       { System.out.println("PLUS"); return Token.PLUS; }
"-"       { System.out.println("MINUS"); return Token.MINUS; }
"*"       { System.out.println("MULTIPLY"); return Token.MULTIPLY; }
"/"       { System.out.println("DIVIDE"); return Token.DIVIDE; }

/* Unrecognized characters */
. { System.out.println("ERROR(" + yytext() + ")"); return Token.ERROR; }

%%

/* Additional Java code */

public class SimpleLexer {
    public static void main(String[] args) throws IOException {
        SimpleLexer lexer = new SimpleLexer(System.in);
        while (lexer.yylex() != null) { /* Keep lexing until input is exhausted */ }
    }
}
