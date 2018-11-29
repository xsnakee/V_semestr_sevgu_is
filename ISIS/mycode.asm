page 60,132
TITLE lab4_exe.asm

STACKSG SEGMENT PARA 'stack'
        ;DB 16 DUP('STACKSG')
STACKSG ENDS

DATASG SEGMENT PARA 'data' 
    MY_LATEST_NUMS DW 055h ;55h = 85d
    MAS_SIZE DW 0B4h;0B4h      ;100+10*i(where i = 5)    = 180D
    MAS 0B4h DUP(?) 
    
    MAS1_2_SIZE DB 5
    MAS1 5 DUP(1,2,3,4,5) 
    MAS2 25 DUP(?)
    
    ;MATRIX_CALC_FORMULA:    C=(A-i)+B 
              
    A DB 10
    B DB 5     
    I DB 5  
    MATRIX DW (A-i+B)
           
   
DATASG ENDS

CODESG SEGMENT PARA 'code'
  ;INIT MAS ARRAY
INIT_THE_MASS PROC
	MOV AX, 00h  
	MOV CX, 0B4h
	LEA BX, [MAS + 100h]
	cycle:     
	INC AL    
	MOV [DS + BX], AL
	INC BX
	DEC CX
	JNZ cycle 
	RET
INIT_THE_MASS ENDP

;COPY ARRAYS
COPY_MAS1_TO_MAS2 PROC 
	LEA SI, [MAS1 + 100h]
	LEA DI, [MAS2 + 100h]
	MOV CX, 25
	REP MOVSB
	RET
COPY_MAS1_TO_MAS2 ENDP

;FIND MY_LATEST_NUMS VAL IN ARRAY
FIND_MY_NUMS_IN_MAS PROC
	LEA SI, [MAS + 100h]
	MOV AX, 00h
	find_cycle:          
	
	MOV CX, 0b4h 
	
	MOV DX, 055h
	CMP [SI],DX
	JZ find_result
	INC SI
	INC AX
	JMP find_cycle
	find_result:
	MOV BX, AX

	RET
FIND_MY_NUMS_IN_MAS ENDP

    
;MAIN_PROG    
 BEGIN PROC FAR   
        ASSUME  SS: STACKSG, CS:CODESG, DS:DATASG, ES:NOTHING

        PUSH DS
        
        CALL INIT_THE_MASS
        CALL COPY_MAS1_TO_MAS2
        CALL FIND_MY_NUMS_IN_MAS    
        
        RET 
        
 BEGIN ENDP     
 
 

CODESG ENDS
END    BEGIN 


 
 
