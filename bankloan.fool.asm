push 0
lhp
sw
lhp
push 1
add
shp
push 9994
lw
lhp
sw
lhp
lhp
push 1
add
shp
lhp
sw
lhp
push 1
add
shp
lhp
sw
lhp
push 1
add
shp
push 9996
lw
lhp
sw
lhp
lhp
push 1
add
shp
lfp
lfp
push -5
add
lw
lfp
push 1
add
lw
js
push -4
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
lfp
push -3
add
lw
lfp
push -1
add
lw
js
b label1
label0: 
push 0
label1: 
print
halt
