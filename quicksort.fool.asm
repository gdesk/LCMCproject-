push 0
lfp
push function0
lfp
push function2
lfp
push function3
lfp
push function5
nulllfp
lfp
push -10
lfp
add
lw
lfp
push -8
add
lw
lfp
push -9
add
lw
js
lfp
push -2
add
lw
lfp
push -3
add
lw
js
halt

makeList:
function1:
cfp
lra
nullsrv
sra
pop
pop
pop
sfp
lrv
lra
js

printList:
function0:
cfp
lra
lfp
push function1
push 1
lfp
add
lw
push -1
beq label2
push 0
b label3
label2: 
push 1
label3: 
push 1
beq label0
lfp
nullprint
lfp
nulllfp
lw
push -2
add
lw
lfp
lw
push -3
add
lw
js
lfp
push -2
add
lw
lfp
push -3
add
lw
js
b label1
label0: 
push -1
label1: 
srv
pop
pop
sra
pop
pop
sfp
lrv
lra
js

append:
function2:
cfp
lra
push 1
lfp
add
lw
push -1
beq label6
push 0
b label7
label6: 
push 1
label7: 
push 1
beq label4
nullb label5
label4: 
push 2
lfp
add
lw
label5: 
srv
sra
pop
pop
pop
sfp
lrv
lra
js

accept:
function4:
cfp
lra
push 3
lfp
lw
add
lw
push 1
beq label8
push 1
lfp
add
lw
push 0
beq label10
push 0
b label11
label10:
push 1
label11:
b label9
label8: 
push 1
lfp
add
lw
label9: 
srv
sra
pop
pop
sfp
lrv
lra
js

filter:
function3:
cfp
lra
lfp
push function4
push 1
lfp
add
lw
push -1
beq label14
push 0
b label15
label14: 
push 1
label15: 
push 1
beq label12
lfp
nullpush 2
lfp
add
lw
bleq label18
push 0
b label19
label18:
push 1
label19:
lfp
push -2
add
lw
lfp
push -3
add
lw
js
push 1
beq label16
lfp
push 3
lfp
add
lw
push 2
lfp
add
lw
nulllfp
lw
push -6
add
lw
lfp
lw
push -7
add
lw
js
b label17
label16: 
nulllabel17: 
b label13
label12: 
push -1
label13: 
srv
pop
pop
sra
pop
pop
pop
pop
sfp
lrv
lra
js

quicksort:
function5:
cfp
lra
push 1
lfp
add
lw
push -1
beq label22
push 0
b label23
label22: 
push 1
label23: 
push 1
beq label20
nullb label21
label20: 
push 0
label21: 
push 1
lfp
add
lw
push -1
beq label26
push 0
b label27
label26: 
push 1
label27: 
push 1
beq label24
lfp
nulllfp
lfp
push 1
push -2
lfp
add
lw
nulllfp
lw
push -6
add
lw
lfp
lw
push -7
add
lw
js
lfp
lw
push -8
add
lw
lfp
lw
push -9
add
lw
js
lfp
lw
push -4
add
lw
lfp
lw
push -5
add
lw
js
b label25
label24: 
push -1
label25: 
srv
pop
sra
pop
pop
sfp
lrv
lra
js
