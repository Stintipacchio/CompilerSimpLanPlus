grammar SimpLanPlus ;

prog   : exp    	                                                                                                    #singleExp
       | (dec)+ (stm)* (exp)?                                                                                           #multipleExp
       ;

dec    : type ID ';'                                                                                                    #varDec
       | type ID '(' ( param ( ',' param)* )? ')' '{' body '}'                                                          #funDec
       ;

param  : type ID ;

body   : (dec)* (stm)* (exp)?
	   ;

type   : 'int'
       | 'bool'
       | 'void'
       ;

stm    : ID '=' exp ';'                                                                                                 #stmAsg
       | ID '(' (exp (',' exp)* )? ')' ';'                                                                              #stmCallFun
       | 'if' '(' condition=exp ')' '{' thenBranch=stmThenBranch '}' ('else' '{' elseBranch=stmElseBranch '}')?         #stmIf
	   ;


stmThenBranch    : (stm)+ ;
stmElseBranch    : (stm)+ ;


exp    :  INTEGER                                                                                                       #expInt
       | 'true'                                                                                                         #expTrue
       | 'false'                                                                                                        #expFalse
       | ID                                                                                                             #expId
       | '!' exp                                                                                                        #expNotId
       | e1=exp (op='*' | op='/') e2=exp                                                                                #expMulDiv
       | e1=exp (op='+' | op='-') e2=exp                                                                                #expPlusMinus
       | e1=exp (op='>' | op='<' | op='>=' | op='<=' | op='==') e2=exp                                                  #expReop
       | e1=exp (op='&&' | op='||') e2=exp                                                                              #expAndOr
       | 'if' '(' condition=exp ')' '{' thenBranch=expThenBranch '}' 'else' '{' elseBranch=expElseBranch '}'            #expIf
       | '(' exp ')'                                                                                                    #expBracket
       | ID '(' (exp (',' exp)* )? ')'                                                                                  #expCallFun
       ;

expThenBranch    : (stm)* exp ;
expElseBranch    : (stm)* exp ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

//Numbers
fragment DIGIT  : '0'..'9';
INTEGER         : DIGIT+;

//IDs
fragment CHAR   : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS     : '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;
ERR             : .  -> channel(HIDDEN);