import antlr.SimpLanPlusParser;
import ast.Node;
import org.antlr.v4.runtime.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import ast.SimpLanPlusVisitorImpl;
import semanticanalysis.SymbolTable;



public class Main {

    public static void main(String[] args) throws Exception {

        String input = new String(Files.readAllBytes(Paths.get("src/input.txt")));

        //Exercise 1
        CharStream stream = CharStreams.fromString(input);
        antlr.SimpLanPlusLexer lexer = new antlr.SimpLanPlusLexer(stream);
        List<Token> lexerErrors = new ArrayList<>();
        Token token = lexer.nextToken();

        while (token.getType() != antlr.SimpLanPlusLexer.EOF) {
            if (token.getType() == antlr.SimpLanPlusLexer.ERR) {
                lexerErrors.add(token);
            }
            token = lexer.nextToken();
        }

        System.out.println("Numero di errori lessicali: "+ lexerErrors.size());

        File f = new File("out/errors.txt");
        if (!f.exists()) {
            f.createNewFile();
        } else {
            f.delete();
            f.createNewFile();
        }

        for (int i = 0; i < lexerErrors.size(); i++) {
            int errLine = lexerErrors.get(i).getLine();
            String errStr = lexerErrors.get(i).getText();
            int errPos = lexerErrors.get(i).getCharPositionInLine() + 1;
            String toWrite = "Errore " + i + 1 + ": Linea " + errLine + ", carattere numero " + errPos + " -> " + errStr + "\n";
            Files.write(Paths.get("out/errors.txt"), toWrite.getBytes(), StandardOpenOption.APPEND);
        }
        //analisi sintattica


        //Exercise 2-3
        CharStream stream2 = CharStreams.fromString(input);
        antlr.SimpLanPlusLexer lexer2 = new antlr.SimpLanPlusLexer(stream2);
        CommonTokenStream TokenStream = new CommonTokenStream(lexer2);
        SimpLanPlusParser parser = new SimpLanPlusParser(TokenStream);
        ast.SimpLanPlusVisitorImpl visita_grammatica = new ast.SimpLanPlusVisitorImpl();

        Node albero_grammatica = visita_grammatica.visit(parser.prog());
        SymbolTable symbol_table = new SymbolTable();
        int offset = 0;
        ArrayList<SemanticError> errors = albero_grammatica.checkSemantics(symbol_table, offset);



    }
}

