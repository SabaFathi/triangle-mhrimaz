package ir.ac.kntu;

import java.util.Scanner;

public class Triangle{

    public static void main(String[] args){


        int first,second,third,max,middle,min;
	Scanner scanner = new Scanner(System.in);        
        first = scanner.nextInt();
        second = scanner.nextInt();        
        third = scanner.nextInt();       
        scanner.close();
	if(first < second){
            max = second;
            min = first;
	}
        else{
	    max = first;
	    min = second;
	}
	
	if(max < third){
	    middle = max;
	    max = third;
	}
	else if(min > third){
	    middle = min;
	    min = third;
	}
	else{
	    middle = third;
	}

	if(min + middle <= max){
	    System.out.println("INVALID");
	}
	else{
	    if(middle == max){
	        if(middle == min){
		    System.out.println("EQUILATERAL");
		}
	        else{
		    System.out.println("ISOSCELES");
		}
	    }
	    else if(middle == min){
	        if(middle == max){
	            System.out.println("EQUILATERAL");
	        }
		else{
		    System.out.println("ISOSCELES");
		}
	    }
	    else{
	        System.out.println("SCALENE");
	    }
	} 
    }
}

