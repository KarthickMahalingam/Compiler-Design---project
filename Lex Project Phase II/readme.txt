1. Change to the folder which include fp.l and run the below command
java -jar ../jflex-1.6.1/lib/jflex-1.6.1.jar fp.l

2. Then you will get Lexer.java file. Compile it with the below command.
javac  Lexer.java

3. Then we can use sample.fp to test the generated lexer.
java  Lexer sample.fp

Below is the output based on using sample.fp:

D:\Project\lexical_project_group_9>java Lexer sample.fp
{
Program
identifer: Sample
{
Function
identifer: factoryr
identifer: VAL
{
if
{
<
identifer: VAL
integer:  0
}
then
{
=
identifer: retVal
integer: -1
}
else
{
=
identifer: retVal
integer:  1
}
{
while
{
>
identifer: VAL
integer:  0
}
do
{
=
identifer: retVal
{
*
identifer: retVal
identifer: VAL
}
}
{
=
identifer: VAL
{
-
identifer: VAL
integer:  1
}
}
}
}
return
identifer: retVal
}
{
print
{
identifer: facto
integer:  999
}
}
}
The symbol table is:
BLOCK0::  --> Sample:1 --> <:4 --> else:3 --> facto:14
BLOCK1::  --> then:3 --> =:5 --> =:6 --> do:7 --> =:9 --> =:11
BLOCK2::  --> VAL:2 --> if:3 --> VAL:4 --> retVal:5 --> retVal:6 --> while:7 --> >:8 --> VAL:8 --> retVal:9 --> *:10 --> retVal:10 --> VAL:10 --> VAL:11 --> VAL:12 --> return:2 --> retVal:2 --> print:13
BLOCK3::  --> Program:1 --> Function:2
BLOCK4::  --> factoryr:2

D:\Project\lexical_project_group_9>