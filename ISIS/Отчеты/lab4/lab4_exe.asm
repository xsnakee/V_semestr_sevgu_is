page 60,132
TITLE LAB4_EXE.asm

STACKSG SEGMENT PARA "stack"
    DB 16 DUP('STACKSG')
STACKSG ENDS

DATASG SEGMENT PARA 'data'
    MY_LATEST_NUMS DB 055h ;55h = 85d
    MAS_SIZE DW 0B4h      ;100+10*i(where i = 5)    = 180D
    MAS MAS_SIZE DUP(1,2,3)  
    
    MAS1_2_SIZE DB 5
    MAS1 MAS1_2_SIZE DUP(1,2,3,4,5) 
    MAS2 DB ?
    
    ;MATRIX_CALC_FORMULA:    C=(A-i)+B 
              
    A DB 10
    B DB 5     
    I DB 5  
    MATRIX DW (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) 
           DW (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) 
           DW (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) 
           DW (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) 
           DW (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) 
           DW (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) 
           DW (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) 
           DW (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) (A-B*i) 
           
   
DATASG ENDS

CODESG SEGMENT PARA 'code'
  ;INIT MAS ARRAY
INIT_THE_MASS PROC
	MOV AX, 00h
	LEA CX, MAS_SIZE
	cycle:
	INC AL
	LEA DI, MAS
	MOV [DI], AL
	LOOP cycle  
	RET
INIT_THE_MASS ENDP

;COPY ARRAYS
COPY_MAS1_TO_MAS2 PROC 
	LEA SI, MAS1
	LEA DI, MAS2
	LEA CX, MAS_SIZE
	REP MOVSW
	RET
COPY_MAS1_TO_MAS2 ENDP

;FIND MY_LATEST_NUMS VAL IN ARRAY
FIND_MY_NUMS_IN_MAS PROC
	LEA SI, MAS
	MOV AX, 00h
	find_cycle:          
	
	LEA CX, MAS_SIZE 
	
	LEA DX, MY_LATEST_NUMS
	CMP [SI],DX
	
	JZ find_result
	INC AX
	JMP find_cycle
	find_result:
	MOV BX, AX

	RET
FIND_MY_NUMS_IN_MAS ENDP

    
;MAIN_PROG    
 BEGIN PROC FAR
        ASSUME SS:STACKSG, CS:CODESG, DS:DATASG, ES:NOTHING
        PUSH DS

        CALL INIT_THE_MASS
        CALL COPY_MAS1_TO_MAS2
        CALL FIND_MY_NUMS_IN_MAS    
        
        RET 
        
 BEGIN ENDP     
 
 

CODESG ENDS
END    BEGIN 


 
 
