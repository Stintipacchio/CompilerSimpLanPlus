import antlr.SimpLanPlusParser;
import ast.ErrorType;
import ast.Node;
import ast.SVMVisitorImpl;
import evaluator.ExecuteVM;
import org.antlr.v4.runtime.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import ast.SimpLanPlusVisitorImpl;
import parser.SVMLexer;
import parser.SVMParser;
import semanticanalysis.SymbolTable;
import semanticanalysis.SemanticError;



public class Main {

    public static void main(String[] args) throws Exception {

        File inputFile = new File("./src/input.txt");

        String input = new String(Files.readAllBytes(Paths.get(inputFile.getAbsolutePath())));

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

        if(!lexerErrors.isEmpty()) {
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

            throw new Error("Numero di errori lessicali: " + lexerErrors.size());
        }

        //analisi sintattica

        //Exercise 2-3
        CharStream stream2 = CharStreams.fromString(input);
        antlr.SimpLanPlusLexer lexer2 = new antlr.SimpLanPlusLexer(stream2);
        CommonTokenStream TokenStream = new CommonTokenStream(lexer2);
        SimpLanPlusParser parser = new SimpLanPlusParser(TokenStream);
        SimpLanPlusVisitorImpl visita_grammatica = new SimpLanPlusVisitorImpl();

        Node albero_grammatica = visita_grammatica.visit(parser.prog());
        SymbolTable symbol_table = new SymbolTable();
        int offset = 0;
        ArrayList<SemanticError> errors = albero_grammatica.checkSemantics(symbol_table, offset);


        if(errors.size()>0){
            System.err.println("You had " + errors.size() + " errors:");
            for(SemanticError e : errors) {
                System.err.println("\t" + e);
            }
            throw new Error();
        }



        Node type = (Node)albero_grammatica.typeCheck(); //type-checking bottom-up

        if (type instanceof ErrorType)
            throw new Error("Type checking is WRONG!");
        else {
            System.out.println("CheckSemantic ok!");
            System.out.println("Type checking ok!\n");
        }




        String code=albero_grammatica.codeGeneration();
        BufferedWriter out = new BufferedWriter(new FileWriter("out/Intermediate-code.asm"));
        out.write(code);
        out.close();
        System.out.println("Code generated! Assembling and running generated code.");

        FileInputStream isASM = new FileInputStream("out/Intermediate-code.asm");
        ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
        SVMLexer lexerASM = new SVMLexer(inputASM);
        CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
        SVMParser parserASM = new SVMParser(tokensASM);

        //parserASM.assembly();

        SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
        visitorSVM.visit(parserASM.assembly());

        //System.out.println("You had: "+lexerASM.lexicalErrors+" lexical errors and "+parserASM.getNumberOfSyntaxErrors()+" syntax errors.");
        //if (lexerASM.lexicalErrors>0 || parserASM.getNumberOfSyntaxErrors()>0) System.exit(1);

        System.out.println("Starting Virtual Machine...");
        ExecuteVM vm = new ExecuteVM(visitorSVM.code);
        vm.cpu();
    }
}

