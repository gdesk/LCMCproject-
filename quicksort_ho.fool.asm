push 0
lfp
push function2
lfp
push function4
lfp
push function5
lfp
push function6
lfp
push function9
lfp
push function10
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
push 9997
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
push -12
lfp
add
lw
push -13
lfp
add
lw
push -14
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
function3:
cfp
lra
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
push 9997
lw
lhp
sw
lhp
lhp
push 1
add
shp
srv
sra
pop
pop
pop
sfp
lrv
lra
js

printList:
function2:
cfp
lra
lfp
push function3
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
lfp
lfp
lw
push -3
add
lw
lfp
lw
push -1
add
lw
js
print
lfp
lfp
lfp
lw
push -3
add
lw
lfp
lw
push 0
add
lw
js

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
function4:
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
push 9997
lw
lhp
sw
lhp
lhp
push 1
add
shp
b label5
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

filter:
function5:
cfp
lra
push 1
lfp
add
lw
push -1
beq label10
push 0
b label11
label10: 
push 1
label11: 
push 1
beq label8
lfp
lfp
lfp
lw
push -3
add
lw
lfp
lw
push -1
add
lw
js

lfp
push 3
add
lw
lfp
push 2
add
lw
js
push 1
beq label12
lfp
push 3
lfp
add
lw
push 2
lfp
add
lw
lfp
lfp
lw
push -3
add
lw
lfp
lw
push 0
add
lw
js

lfp
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
b label13
label12: 
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
push 9997
lw
lhp
sw
lhp
lhp
push 1
add
shp
label13: 
b label9
label8: 
push -1
label9: 
srv
sra
pop
pop
pop
pop
sfp
lrv
lra
js

beforePivot:
function7:
cfp
lra
lfp
push -2
lfp
lw
add
lw
push 1
lfp
add
lw

lfp
lw
push 3
add
lw
lfp
lw
push 2
add
lw
js
srv
sra
pop
pop
sfp
lrv
lra
js

afterPivot:
function8:
cfp
lra
lfp
push -2
lfp
lw
add
lw
push 1
lfp
add
lw

lfp
lw
push 3
add
lw
lfp
lw
push 2
add
lw
js
push 0
beq label18
push 0
b label19
label18:
push 1
label19:
srv
sra
pop
pop
sfp
lrv
lra
js

quicksort:
function6:
cfp
lra
push 1
lfp
add
lw
push -1
beq label16
push 0
b label17
label16: 
push 1
label17: 
push 1
beq label14
lfp
lfp
lw
push -3
add
lw
lfp
lw
push -1
add
lw
js
b label15
label14: 
push 0
label15: 
lfp
push function7
lfp
push function8
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
lfp
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
push 9997
lw
lhp
sw
lhp
lhp
push 1
add
shp
lfp
push 3
lfp
add
lw
push 2
lfp
add
lw
lfp
push -3
lfp
add
lw
push -4
lfp
add
lw
lfp
lfp
lw
push -3
add
lw
lfp
lw
push 0
add
lw
js

lfp
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
b label21
label20: 
push -1
label21: 
srv
pop
pop
pop
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

inc:
function9:
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
bleq label24
push 0
b label25
label24:
push 1
label25:
srv
sra
pop
pop
pop
sfp
lrv
lra
js

dec:
function10:
cfp
lra
push 2
lfp
add
lw
push 1
lfp
add
lw
bleq label26
push 0
b label27
label26:
push 1
label27:
srv
sra
pop
pop
pop
sfp
lrv
lra
js
