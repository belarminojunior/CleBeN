%%
%standalone
%class Cleben_Analex

%{

int numB = 0;
String R = "palavra reservada";
%}



// Palavras_reservadas = (

// )
IDENTIFICADOR = [a-zA-Z][a-zA-Z0-9_]*
DELIMITADOR = ["("|")"|"{"|"}"|"["|"]"]
OPERADOR_BOOLEANO=["and"|"or"]
BRANCO = [\n || \t]
INTEIRO = 0|[1-9][0-9]*
PONTO_FLUTUANTE = [0-9][0-9]*"."[0-9]+
OPERADORES_MATEMATICOS = ("+"|"-"|"\*"|"/"|"%"|"<"|">"|"<="|">=")
OUTROS_SIMBOLOS = (","|"."|"''"| """"|"="|"^")
LINE_COMMENT = ["//"].* 

%%
"start"        {  System.out.println(yytext()+"->: "+R)}
"if"          { System.out.println(yytext()+"-> "+R)}
"else"        {  System.out.println(yytext()+"->"+R)}
"elsif"       {  System.out.println(yytext()+"-> "+R)}
"while"       {  System.out.println(yytext()+"-> "+R)}
"do"           {  System.out.println(yytext()+"-> "+R)}
"return"       {  System.out.println(yytext()+"-> "+R)}
"function"      {  System.out.println(yytext()+"->"+R)}
"assign"      {  System.out.println(yytext()+"-> "+R)}
"to"          { System.out.println(yytext()+"-> "+R)}
"true"        {  System.out.println(yytext()+" "+R)}
"false"       { System.out.println(yytext()+" ->"+R)}
"assign"        {System.out.println(yytext()+"->atribuicao: "+R)}
"number"        {System.out.println(yytext()+"->Tipo int: "+R)}
"float"         {System.out.println(yytext()+"->Tipo float: "+R)}
"eq"         { System.out.println(yytext()+"->comparador de igualdade: "+R)}
"neq"         { System.out.println(yytext()+"->comparador de diferenca: "+R)}
"nor"         { System.out.println(yytext()+"->Operador de negacao: "+R)}
{INTEIRO}       { System.out.println(yytext()+"->Numero Inteiro")}
{IDENTIFICADOR}     { System.out.println(yytext()+"->Identificador")}
{DELIMITADOR}     {System.out.println(yytext()+"->Delimitador")}
{OPERADORES_MATEMATICOS}    {System.out.println("->DOperador Matematico")}
{PONTO_FLUTUANTE}           {System.out.println("->Float")}
{LINE_COMMENT}              {System.out.println(yytext()+"->Comentario")}
{BRANCO}                  {/*Ignorar*/}

.               {System.out.println("Token mal formado: " + yytext());}



