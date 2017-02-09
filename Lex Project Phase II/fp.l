import java.io.*;
import java.util.ArrayList;

%%
%public
%class Lexer
%byaccj
%unicode
%line
%column

%{
static ArrayList<String> ids=new ArrayList<String>();
public int getLine() {
		return yyline;
}
private Parser yyparser;
public Lexer (java.io.Reader r, Parser yyparser) {
		//Lexer lexer=new Lexer(r);	
		//lexer.yylex();
		this(r);
		this.yyparser=yyparser;
		System.out.println("Called me");
	}
	
%}

lineTerminator = \r|\n|\r\n|\
inputChar = [^\r\n]
whiteSpace = {lineTerminator}|[ \t\f]
comment = "//"({inputChar}|[ ])*{lineTerminator}?
digit=[0-9]
letter=[a-zA-Z]
chars=[a-zA-Z0-9]|\
identifer={letter}({letter}|{digit}){7}$
integer=[-]?{whiteSpace}*{digit}+
float={integer}"."{digit}+
string="("({chars}|[ ])*")"
boolean=T|F
%%
[0-9]+					   {yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));return Parser.INTEGER;}
"("                        return "{";   								// { System.out.println("("); }
")"                        return "}";      							// { System.out.println(")"); }
"{"                        ids.add("{");    							// { System.out.println("{"); ids.add("{");}
"}"                        ids.add("}"};    							// { System.out.println("}"); ids.add("}");}
"/"                        return Parser.DIVIDE;   							// { System.out.println("/"); }
"+"                        return Parser.ADD;      							// { System.out.println("+"); }
"-"                        return Parser.MINUS;    							// { System.out.println("-"); }
"*"                        return Parser.MULTIPLY; 							// { System.out.println("*"); }
"%"						   return Parser.MOD;	    							// { System.out.println("%"); }
"=="                       return Parser.CHECKEQUALS;      					// { System.out.println("==");}
"<="                       return Parser.LESSEQ;   							// { System.out.println("<=");}
">="                       return Parser.GREATEREQ;							// { System.out.println(">=");}
"!="                       return Parser.NOTEQ;    							// { System.out.println("!=");}
"="                        return Parser.EQUAL;    							// { System.out.println("="); }
">"                        return Parser.GREATER;  							// { System.out.println(">"); }
"<"                        return Parser.LESS;     							// { System.out.println("<"); }
"Program"				   return Parser.PROGRAM;  							// { System.out.println("Program"); }
"Function"				   return Parser.FUNCTION;	    						// { System.out.println("Function");}
"return"				   return Parser.RETURN;	   							// { System.out.println("return"); }
"if"					   return Parser.IF;	   								// { System.out.println("if");}
"then"					   return Parser.THEN;	    							// { System.out.println("then"); }
"else"					   return Parser.ELSE;    								// { System.out.println("else");}
"while"					   return Parser.WHILE;    							// { System.out.println("while"); }
"do"					   return Parser.DO;    								// { System.out.println("do");}
"or"					   return Parser.OR;		   							// { System.out.println("or"); }
"and"					   return Parser.AND;		    						// { System.out.println("and");}
"print"					   return Parser.PRINT;		    					// { System.out.println("print"); }
"T"						   return Parser.TRUE;		   							// { System.out.println("Boolean: True");}
"F"						   return Parser.FALSE;	  							// { System.out.println("Boolean: False");}
{identifer}                ids.add(yytext()+" ");return Parser.IDENTIFIER;     //{ System.out.println("identifer: "+yytext()); ids.add(yytext()+" ");}
{integer}				   return Parser.INTEGER;	   							// { System.out.println("integer: "+yytext());}
{float}                    return Parser.FLOAT;	    						// {System.out.println("float: "+yytext());}
{string}                   return Parser.STRING;    							// {System.out.println("character-string: "+yytext());}
[ \t]+               	   { /* ignore */ }
{comment}				   return Parser.COMMENT_LINE;   							// { /* ignore */ }