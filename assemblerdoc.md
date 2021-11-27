# Assembler Documentation
## Assembling Programs
1. To run programs on the processor, first assemble the memory image.

```
->Write an assembly programme in 32bit-SIMPLERISCPROCESSOR/assembler/TestCase.asm 
->javac assembler.java 
->java assembler
->A new file named "assembleroutput" will be created which is memory image
```
2. Load the memory image inside logism by right clicking on the RAM module and selecting `Load Image...`  
- The programs are loaded at starting address 0x0000
- Each instruction in the assembly language takes up one memory word of 32-bit.
## Assembler Language Commands


### Load  
Loads value from memory.
```
load [Destination] [Source]
load r3 r2
```

### Store
Store a value into memory.
```
store [Data] [Address]
store r3 r2
```


### Mov  
Move data from one register to another.
```
mov [Destination] [Source]
mov r3 r2
```


### branch 
Jump to particular address[PC+offset].
```
b offset
```


### branch if equal
Jump to particular address[PC+offset] if  flags.E is 1.
```
beq offset
```

### branch if greater
Jump to particular address[PC+offset] if  flags.GT is 1.
```
bgt offset
```
### Nop  
Do Nothing
```
nop
```

### Add, Subtract, And, Or, XOR, XNOR
Perform the arithmetic opration and store result to destination register

```
op [destination] [source1] [source2]

add r3 r1 r2 
sub r3 r1 r2
and r3 r1 r2
or  r3 r1 r2
xor r3 r1 r2
xnor r3 r1 r2


```

### Not
Invert bits and store to destination register.
```
not [destination] [source]
```

```

### Cmp
Compares two values and sets flag register. 
```
If Source1 > Source2 then  flags.GT is set.
If Source1 == Source2 then flags.E is set.
```
```
cmp [source1] [source2]
cmp r1 r2
```
