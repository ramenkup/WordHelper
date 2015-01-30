# Your code goes below this line

						
			

inorder:	.asciiz	"The integers in order:"
newline:	.asciiz	"\n"					#new line space in-between forward and backwards versions
backheader:	.asciiz	"The integers in backwards order:"
i:			.word	0
end:		.word	-999


.text
main:

	# Function prologue -- even main has one
	addiu	$sp,	$sp,	-24		# allocate stack space -- default of 24 here
	sw		$fp, 	0($sp)     		# save caller's frame pointer
	sw    	$ra, 	4($sp)     		# save return address
	addiu 	$fp, 	$sp,	20  	# setup main's frame pointer
        
	la		$t0,	forward			#load forward
	lb		$s0,	0($t0)

	la		$t0,	backward		#load backward
	lb		$s1,	0($t0)

	la		$t0,	i				#load	i
	lw		$s3,	0($t0)			#Do you need an offset in load byte

	la		$t0,	numbers
	lw		$s4,	0($t0)

	la		$t0,	numbers			#load array numbers list
	addi 	$s6,	$t0,	0

	la		$t0,	end
	lw		$s5,	0($t0)			#load end

	addi	$s7,	$zero,	1
	beq		$s0,	$s7,	forprinter

	j		baccheck

forprinter:
	la		$a0,	inorder			#print in order header
	addi	$v0,	$zero,	4		
	
	syscall
	la		$a0,	newline			#load new line
	addi	$v0,	$zero,	4		#add 4 to $v0 to properly print
	syscall							#print
	j forloop




forloop:
	lw		$a0,	0($s6)				#Temp reg $a0= save[i]
	beq		$a0,	$s5,	baccheck	#jump to check for backwards if reached the end
	addi	$v0,	$zero,	1			#add 1 to $v0 to properly print
	syscall
	la		$a0,	newline				#load new line
	addi	$v0,	$zero,	4			#add 4 to $v0 to properly print
	syscall								#print
	addi 	$s6, 	$s6, 	4 			#add 4 for next iteration
	j		forloop

baccheck:
	la		$s0,	backward			#s0 equals backward
	lb		$s0,	0($s0)
	beq		$s0,	$s7,	bacprinter	#checking if s0==1
	j exit

bacprinter:  #printer header and space
	addi	$s7,	$zero,	1
	la,		$s0,	forward
	lb		$s0,	0($s0)
	bne		$s0,	$s7,	printBackHeader

spacer:
	la		$a0,	newline			#load new line
	addi	$v0,	$zero,	4		#add 4 to $v0 to properly print
	syscall							#print


printBackHeader:	
	la		$a0,	backheader
	addi	$v0,	$zero,	4
	syscall
	la		$a0,	newline			#load new line
	addi	$v0,	$zero,	4		#add 4 to $v0 to properly print
	syscall							#print
	la 		$t0,	numbers
	j bacloop



bacloop: #look for -999
	lw		$t1,	0($t0)
	beq		$t1,	$s5,	bacloopersetup
	addi	$t0,	4

	j bacloop

bacloopersetup:
	addi	$t0,	$t0,	-4
	la		$s6,	numbers
	slt 	$t2,	$t0, 	$s6		#comparing adresses
	bne 	$t2, 	$zero, 	exit

baclooper:	#print from -999
	lw		$t2,	0($t0)
	add		$a0,	$zero,	$t2
	addi	$v0,	$zero,	1
	syscall
	la		$a0,	newline			#load new line
	addi	$v0,	$zero,	4		#add 4 to $v0 to properly print
	syscall							#print
	la		$s6,	numbers
	addi	$t0,	$t0,	-4
	slt 	$t2,	$t0, 	$s6		#comparing adresses
	beq 	$t2, 	$zero, 	baclooper

exit:
	#epilouge
	lw		$ra, 	4($sp)     		# get return address from stack
	lw    	$fp, 	0($sp)     		# restore the caller's frame pointer
	addiu 	$sp,	$sp,	24 		# restore the caller's stack pointer
	jr    	$ra            			# return to caller's code