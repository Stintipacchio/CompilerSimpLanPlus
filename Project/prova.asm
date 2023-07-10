pushr FP 
move SP FP 
addi FP 1 
move AL T1
pushr T1 
storei A0 10
pushr A0
move FP AL 
subi AL 1 
jsub function0
halt
