push 0
push function0
push function1
lfp
push 2
lfp
push -3
add
lw
lfp
push -4
add
lw
js
print
halt

function0:
cfp
lra
lfp
push 7
push 5
lfp
push 2
add
lw
lfp
push 1
add
lw
js
srv
sra
pop
pop
pop
sfp
lrv
lra
js

function2:
cfp
lra
push 1
lfp
add
lw
push 2
lfp
add
lw
add
push 1
lfp
lw
add
lw
mult
srv
sra
pop
pop
pop
sfp
lrv
lra
js

function1:
cfp
lra
push function2
lfp
push -2
lfp
add
lw
push -3
lfp
add
lw
lfp
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
