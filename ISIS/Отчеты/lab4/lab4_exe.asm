page 60,132
TITLE lab4_exe.asm  

STACKSG SEGMENT PARA 'stack'
        ;DB 16 DUP('STACKSG')
STACKSG ENDS

DATASG SEGMENT PARA 'data' 
    MY_LATEST_NUMS DW 055h
    MAS_SIZE DW 0B4h
    MAS 0B4h DUP(?) 
    
    MAS1_2_SIZE DB 5
    MAS1 5 DUP(1,2,3,4,5) 
    MAS2 25 DUP(?)
    
    ;MATRIX_CALC_FORMULA:    C=(A-i)+B 
              
    A DB 10
    B DB 5     
    I DB 5  
    MATRIX DW 1,2,3,4,5,6,7,8
           DW 9,10,11,12,13,14,15,16,17
           DW 21,22,23,24,25,26,27,28
           DW 39,310,311,312,313,314,315,316,317
           DW 31,32,33,34,35,36,37,38
           DW 29,210,211,212,213,214,215,216,217
           DW 41,42,43,44,45,46,47,48
           DW 94,104,114,124,134,144,154,164,174
           
           
   
DATASG ENDS

CODESG SEGMENT PARA 'code'
  ;INIT MAS ARRAY
INIT_THE_MASS PROC
	MOV AX, 00h  
	MOV CX, 0B4h
	LEA SI, [MAS + 100h]
	cycle:     
	INC AL    
	MOV [SI], AL
	INC SI
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
	MOV BX, 00h
	MOV CX, 0b4h
	MOV DX, 055h 
	
	find_cycle:
	LODSB
	
	CMP AX, DX
	JZ find_result
	INC BX
	loop find_cycle
	find_result:
	RET
FIND_MY_NUMS_IN_MAS ENDP

;MATRIX
FIND_DIAGONAL_VAL_IN_MATRIX PROC
    ASSUME ES:02020h
    
    LEA DI, ES:300h
    LEA SI, [MATRIX + 100h]
           
    MOV DX, 08h
    MOV CX, 08h
    cycle1:         
       MOVSW
       ADD SI, DX         
    loop cycle1
    
        
    RET
    
FIND_DIAGONAL_VAL_IN_MATRIX ENDP
         
         
;MAIN_PROG    
 BEGIN PROC FAR   
        ASSUME  SS: STACKSG, CS:CODESG, DS:DATASG, ES:NOTHING

        PUSH DS
                
        CALL INIT_THE_MASS
        CALL COPY_MAS1_TO_MAS2
        CALL FIND_MY_NUMS_IN_MAS    
        CALL FIND_DIAGONAL_VAL_IN_MATRIX
        
 BEGIN ENDP     
 
 

CODESG ENDS

END    BEGIN 


 
 
