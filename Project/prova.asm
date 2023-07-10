move SP FP 
pushr FP 
move SP AL 
pushr AL 
pushr A0 
push function0
pushr FP 
move SP FP 
addi FP 1 
move AL T1
pushr T1 
storei A0 5
pushr A0
move FP AL 
subi AL 1 
jsub function0
halt

function0:
pushr RA 
move AL T1 
subi T1 1
store A0 0(T1) 
pushr A0 
storei A0 4
popr T1 
beq T1 A0 label2
storei A0 0 
b label3
label2:
storei A0 1 
label3:
storei T1 1 
beq A0 T1 label0
move AL T1 
subi T1 1
store A0 0(T1) 
pushr A0 
storei A0 4
popr T1 
mul T1 A0 
popr A0 
move AL T1 
store T1 0(T1) 
subi T1 1
load A0 0(T1) 
b label1
label0:
move AL T1 
subi T1 1
store A0 0(T1) 
pushr A0 
storei A0 3
popr T1 
add A0 T1 
sub T1 A0 
popr A0 
move AL T1 
store T1 0(T1) 
subi T1 1
load A0 0(T1) 
label1:
addi SP 0
popr RA 
addi SP 1
pop 
store FP 0(FP) 
move FP AL 
subi AL 1 
pop 
rsub RA 
