package refactored;
import java.io.IOException;
import java.io.Reader;


// Definições de tokens
enum TokenType {
    ASSIGN, TO, TYPE_NUMBER, TYPE_TEXT, NULL, TYPE_LIST, RANGE, FOR, EVERY, IN, DO, ENDFOR,
    WHILE, ENDWHILE, FUNCTION, DOES, RETURN, ENDFUNCTION, IF, THEN, ELSEIF,
    ELSE, ENDIF, PRINT, INCR, DECR, EQ, NEQ, LT, GT, LE, GE, PLUS, MINUS,
    STAR, SLASH, LPAREN, RPAREN, LBRACKET, RBRACKET, COMMA, DOTDOT, ID,
    NUMBER_LITERAL, STRING_LITERAL, EOF, ERROR
}


%%

/* Configurações do JFlex */
%class CleBeNLexer
%unicode
%public
%type TokenType

%{
    private TokenType symbol(TokenType type) {
        return type;
    }

    private TokenType symbol(TokenType type, Object value) {
        return type;
    }

    private void reportError(String message) {
            System.err.println("Lexer Error: " + message);
    }
%}

/* Definições de estados de comentário */
%x COMMENT

/* Palavras-chave para tipos */
TYPE_LIST   = list
TYPE_NUMBER = number
TYPE_TEXT   = text
NULL        = null

/* Palavras-chave */
ASSIGN      = assign
TO          = to
RANGE       = \d+\.\.\d+
FOR         = for
EVERY       = every
IN          = in
DO          = do
ENDFOR      = endfor
WHILE       = while
ENDWHILE    = endwhile
FUNCTION    = function
DOES        = does
RETURN      = return
ENDFUNCTION = endfunction
IF          = if
THEN        = then
ELSEIF      = elseif
ELSE        = else
ENDIF       = endif
PRINT       = print
INCR        = incr
DECR        = decr

/* Operadores e símbolos */
EQ          = eq
NEQ         = neq
LT          = lt
GT          = gt
LE          = le
GE          = ge
PLUS        = \+
MINUS       = -
STAR        = \*
SLASH       = \/
LPAREN      = \(
RPAREN      = \)
LBRACKET    = \[
RBRACKET    = \]
COMMA       = ,
DOTDOT      = \.\.

/* Identificadores e literais */
ID          = \b[a-zA-Z_][a-zA-Z_0-9]*
NUMBER_LITERAL = [0-9]+
STRING_LITERAL = \"([^\\\"]|\\.)*\"

/* Regras de análise léxica */
%%
<YYINITIAL> {
    {ASSIGN}         { return symbol(TokenType.ASSIGN); }
    {TO}             { return symbol(TokenType.TO); }
    {TYPE_LIST}      { return symbol(TokenType.TYPE_LIST); }
    {TYPE_NUMBER}    { return symbol(TokenType.TYPE_NUMBER); }
    {TYPE_TEXT}      { return symbol(TokenType.TYPE_TEXT); }
    {NULL}           { return symbol(TokenType.NULL); }
    {RANGE}          { return symbol(TokenType.RANGE, yytext()); }
    {FOR}            { return symbol(TokenType.FOR); }
    {EVERY}          { return symbol(TokenType.EVERY); }
    {IN}             { return symbol(TokenType.IN); }
    {DO}             { return symbol(TokenType.DO); }
    {ENDFOR}         { return symbol(TokenType.ENDFOR); }
    {WHILE}          { return symbol(TokenType.WHILE); }
    {ENDWHILE}       { return symbol(TokenType.ENDWHILE); }
    {FUNCTION}       { return symbol(TokenType.FUNCTION); }
    {DOES}           { return symbol(TokenType.DOES); }
    {RETURN}         { return symbol(TokenType.RETURN); }
    {ENDFUNCTION}    { return symbol(TokenType.ENDFUNCTION); }
    {IF}             { return symbol(TokenType.IF); }
    {THEN}           { return symbol(TokenType.THEN); }
    {ELSEIF}         { return symbol(TokenType.ELSEIF); }
    {ELSE}           { return symbol(TokenType.ELSE); }
    {ENDIF}          { return symbol(TokenType.ENDIF); }
    {PRINT}          { return symbol(TokenType.PRINT); }
    {INCR}           { return symbol(TokenType.INCR); }
    {DECR}           { return symbol(TokenType.DECR); }
    {EQ}             { return symbol(TokenType.EQ); }
    {NEQ}            { return symbol(TokenType.NEQ); }
    {LT}             { return symbol(TokenType.LT); }
    {GT}             { return symbol(TokenType.GT); }
    {LE}             { return symbol(TokenType.LE); }
    {GE}             { return symbol(TokenType.GE); }
    {PLUS}           { return symbol(TokenType.PLUS); }
    {MINUS}          { return symbol(TokenType.MINUS); }
    {STAR}           { return symbol(TokenType.STAR); }
    {SLASH}          { return symbol(TokenType.SLASH); }
    {LPAREN}         { return symbol(TokenType.LPAREN); }
    {RPAREN}         { return symbol(TokenType.RPAREN); }
    {LBRACKET}       { return symbol(TokenType.LBRACKET); }
    {RBRACKET}       { return symbol(TokenType.RBRACKET); }
    {COMMA}          { return symbol(TokenType.COMMA); }
    {DOTDOT}         { return symbol(TokenType.DOTDOT); }
    {ID}             { return symbol(TokenType.ID, yytext()); }
    {NUMBER_LITERAL} { return symbol(TokenType.NUMBER_LITERAL, Integer.parseInt(yytext())); }
    {STRING_LITERAL} { return symbol(TokenType.STRING_LITERAL, yytext()); }
    \s+              { /* Ignora espaços em branco */ }
    "//"[^\n]*       { /* Ignora comentários de linha única */ }
    "/*"             { yybegin(COMMENT); }
}

<COMMENT> {
    "*/"             { yybegin(YYINITIAL); }
    [^*]+            { /* Ignora conteúdo de comentários */ }
    "*"[^/]*         { /* Ignora conteúdo de comentários */ }
}

.                  { return symbol(TokenType.ERROR); }
<<EOF>>            { return symbol(TokenType.EOF); }
