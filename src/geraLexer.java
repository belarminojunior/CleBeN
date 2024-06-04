import java.io.*;
import jflex.*;
import java.nio.file.Paths;

public class geraLexer {
    public static void main(String[] args) {

		String rootPath = Paths.get("").toAbsolutePath().toString();
		String subPath = "/";

		String file[] = {rootPath + subPath + "regras_CleBeN.flex"};

		jflex.Main.main(file);

	}

}    
