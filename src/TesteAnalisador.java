import java.io.*;
import jflex.*;

public class TesteAnalisador {
    public static void main(String[] args) throws IOException{
        String arq = "programa+na_linguagem_clebeN.txt";
         BufferedReader in = new BufferedReader (new FileReader(arq));
         Cleben_Analex analise = new Cleben_Analex(in);
        
         String exp = in.toString();
         analise.yylex();
         
    //     String expr = "if (i=0; i<=5; i++)" 
	// 		             + "else 2 + 3+a*5/4-16 then";
	// analise.yylex();
        
    }
}