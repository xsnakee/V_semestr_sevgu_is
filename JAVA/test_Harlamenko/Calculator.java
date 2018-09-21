import java.util.Scanner;

public class Calculator {
    public static void main (String[] args){
    
//      if (args.length > 0) {
//    	Scanner in, out;
//        for (int i=0; i<args.length; i++){
//
//          try {
//            if (args[i].equals("-i")) {
//            	File inputFile = new File(args[++i]);
//            	in = new Scanner(inputFile);
//            };
//
//            if (args[i].equals("-o")) {
//            	File outputFile = new File(args[++i]);
//            	out = new Scanner(outputFile);
//            };
//            
//            int c;
//
//            while(in.hasNextLine()) { 
//
//            	String word = in.nextLine();
//            	System.out.println(word);
//            }
//          } finally {
//            if (in != null) {in.close(); };
//            if (out != null) {out.close(); };
//          }
//        }
//      } else {
      	Scanner read = new Scanner(System.in);
 		
        double first;
        double second;
        String operator;
        
        while (true){
          System.out.print("Write expression > ");

          first = read.nextDouble();
          operator = read.next();
          second = read.nextDouble();

          if (operator.equals("*")){
            System.out.println("= " + (first * second));
          }
          if (operator.equals("/")){
            System.out.println("= " + (first / second));
          }
          if (operator.equals("+")){
            System.out.println("= " + (first + second));
          }
          if (operator.equals("-")){
            System.out.println("= " + (first - second));
          }
        }
      }
    } 
//}
