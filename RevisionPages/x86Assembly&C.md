# Assembly and x86

## General purpose registers
---

In CA1 we used **32-bit** registers, any register that is 32-bit starts with E.

Need to know:
- EAX: Accumulator (most arithmetic performed here)
- EBX: Base register (Often used to reference something in memory or storage like an address)
- ECX: Counter register (mainly in loops)
- EDX: Data register (Holds data items such as maybe inputs from user)

Descriptions in brackets are not set in stone, just general use cases. Below is the make up of how many bits are allocated to each style of label ranging from 0 being bit 1 to 63 being the 64<sup>th</sup> bit.

![Accumulator](./x86Accumulator.png)

When you want to move data into a register, you use `mov [register] [operand]`. E.g. `mov eax, 12`.

You can also use `lea [register] [operand]` to move the address of a piece of data or instruction to a register. E.g. `lea ebx, num` this moves the **memory location** of num to ebx. Note we used ebx as we are addressing like stated above.

---
## General arithmetic
---

Add and subtract operations follow a similar format in that it goes `[Operation] [variable to be changed] [variable to be used]`. This view is abstract so here is a few examples `add eax, 12` `sub eax, 14`. So here we have 2 operations, adding 12 to what is in eax and subtracting 14 from whatever is in eax.

Multiplication and division work in a weirder way. These operations only get performed on the value store in `EAX` and get store in `EDX` when performed. This looks like below:

```c++
// Division
mov edx, 0
mov eax, 12
mov ebx, 3
div ebx

// Multiplication
mov edx, 0
mov eax, 3
mov ebx, 4
mul ebx

```
Above in division the following takes place: make edx = 0 to make sure no data already resides there are creates arithmetic mistakes, move 12 to accumulator, move 3 to the base register and divide ebx into eax and store result in edx. This result we can see is 4.

For multiplication, similar operations happen except accumulator = 3, base register = 4 and the result in edx is 12.

---
## Jumps
---

### Flags register

These record the status of a condition check to see if a conditional jump can be satisfied at a given time.

There are a couple of flags and these being:
- Carry flag: checks if there was a bit carry in comparison
- Parity flag: checks lower 8 bits for even amount of 1s
- Zero flag: checks for 0 result
- Sign flag: set 0 if positive and 1 if negative
- Overflow flag: Checks for too large of number or too small of a negative number excluding the signed bit.

But mainly Overflow, Sign and Zero are used.

### Unconditional jump

Jumps no matter of any condition. Has the syntax: `JMP [address of target]`

The address of target is usually a label which can be seen below.

```c++
Start:
      .
      .
      .
   JMP continue   //jump forwards
      .
      .
   JMP start     // jump backwards
continue:
```

Note that `JMP start` never happens as we jump past it no matter what as we don't give a condition.

### Conditional jumps

Will only jump if a condition is satisfied using the flag registers. Essentially think of these like if statements in any HLL. We can test these flags correctly

```
JC    carry flag set   (=1)
JNC   carry flag clear (=0)
JZ    Zero flag set    (=1)
JNZ   Zero flag clear  (=0)
.
.
```

There are a bunch more with the flags that they set which can be found --> [here](http://unixwiz.net/techtips/x86-jumps.html) <--.

In order to get a flag set, we need do some sort of comparison. The most common way to do this can be done by a compare (`cmp`). What we can do is do some comparisons and then jump based on flags. Look at examples below with comments explaining what's going on.

```c++
// Example 1
mov ebx, 0
mov eax, 12
cmp eax, ebx // Will not set the zero flag so ZF = 0
JNZ next     // Will execute because ZF = 0
.
.            // Code that wont be executed
.
next:
.
.           // Code that will be executed
.


// Example 2
mov ebx, 12
mov eax, 20
cmp eax, ebx // Will set the Zero flag = 0 and sign flag and overflow flag = 1
JG next   // Jumps to next flag as greater than flags are satisfied
.
.
.
next:
.
.
.

```

When using `cmp` the first operand is the one that the checks are performed on so for example 2 the check is `is 20 > 12?`.

---
## Loop
---

Another type of "jump" you can use is a loop. This makes use of the `loop [label]` instruction. All this does is perform a conditional jump based on if the ecx is at 0 or not.

We know that the counter for a loop is used in the ecx register meaning that when the loop instruction is executed, ecx is decremented by 1.

```c++
   mov ecx, 10
   mov eax, 0
   mov x, 1    // moves 10 into the counter register
floop:
   add x, eax
   add x, 1
   loop floop  // decrements ecx by 1 and jumps to label
```

All this does above is sum the first 10 numbers. But this makes use of the loop instruction.

---
## Variables for inline assembly
---

For any question on variables for the inline assembler, please refer to C++ documentation on how to declare and use.

---
## Machine-level stacks
---

Two main operations on the stack namely `push [Data/instruction to go on stack]` and `pop [Location for data/instruction to go]`.

Stacks are used when printing variables using inline assembler, ensuring register values when calling external functions are not corrupted and stack-frames. There are other uses but these are the main uses we need to know.

Know that the stacks in x86 grow **Down** in memory. This means if we add things to the stack, and our stack starts at memory address 1032 then when we push another instruction, this is now pushed to 1028.

This means to clear out stack, we can effective just add to the stack pointer in intervals of 4 (remember we are working in 32 bit). This means we can perform `add esp, [some interval of 4]` with esp being the stack pointer pointing to the top instruction.

---
## IO in x86
--

We can use c libraries for this.

In order to print anything, it must be put onto the stack.

Can use `printf` for output and `scanf` for input.

Note to print, the memory reference must be on the stack not the data itself.

### Print
```c++
int main(void) {
 char msg[] = "Hello World\n"; // declare in C
 _asm {
  lea eax, msg   ; put addr of string into eax
  push eax       ; stack the parameter
  call printf    ; use library function
  pop eax        ; remove param
 }
return 0;
}
```

If we want to print values, these must be pushed on the stack first (in order from top to bottom of stack and left to right in print) using formatters. See below.

```c++
#include <stdio.h>
#include <stdlib.h>
int main (void)
{
 char msg[] = "Number is %d\n"
 int n = 157;
 _asm
 {
    push n        ; push int first
    lea eax, msg
    push eax      ; now stack the string
    call printf
    add esp, 8    ; clean 8 bytes from stack
 }
 return 0;
}
```
### Scan

Again for scan we need those formatters. We can use them on the stack aswell. We need to push the varaible on the stack we want it stored in, then the format string, then call the scan.

```c++
char fmt[] = "%d";
int num;
_asm {
   lea eax, num         ; push address of num on stacks
   push eax
   lea eax, fmt         ; now formatted string
   push eax
   call scanf
   add esp, 8           ; clean stack
}
```

Here are all the formatters:

![formatters](./PrintingC.png)
