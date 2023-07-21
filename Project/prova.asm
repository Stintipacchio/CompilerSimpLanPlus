move SP FP 
pushr FP 
move SP AL 
pushr AL 
subi SP 1 
storei A0 1
move AL T1 
subi T1 1
load A0 0(T1) 
move AL T1 
subi T1 1
store A0 0(T1) 
pushr A0 
storei A0 1
popr T1 
add T1 A0 
popr A0 
move AL T1 
subi T1 1
load A0 0(T1) 
halt
