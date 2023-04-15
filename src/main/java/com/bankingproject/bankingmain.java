package com.bankingproject;
import java.util.*;
//Here we have imported the package java.util
//In this project we are giving the interface for this application ,it acts as the view from MVC(model,controller,view)

public class bankingmain {

	public static void main(String[] args) throws Exception {
		
		Scanner bs=new Scanner(System.in);
		//For taking the input from the user
		bankingdao dao=new bankingdao();
		//creating an object for customer class
		customer c1=new customer();
		//creating the object of customer class
		System.out.println("\t\t\t\t$$$$$$$$$$$$Welcome to JavaBank$$$$$$$$$$$$$$$");
		System.out.println("Press 1 for resgistration \n press2 for Login");
		int op=bs.nextInt();
		
		switch(op) {
		
		case 1->{
			
			System.out.println("enter customer id");
	        int cid=bs.nextInt();
	        bs.nextLine();
			System.out.println("enter customer name");
			String cname=bs.nextLine();
			System.out.println("enter customer pin");
	        int cpin=bs.nextInt();
			System.out.println("enter acc balance");
	        int camt=bs.nextInt();
	        
	        c1.cusid=cid;
	        c1.cusname=cname;
	        c1.cuspin=cpin;
	        c1.cusamt=camt;
	        //calling db connection method
	        
	        dao.dbconnection();
	        int res=dao.registercustomer(c1);
	        //we are giving details into database
	        if(res==1) {
	        	//if res is 1 then it is successful else it prints as something went wrong
	        	
	        	System.out.println("acc creation successful");
	        }
	        else {
	        	
	        	System.out.println("something went wrong");

	        }
		}
		case 2->{
			//taking inputs
			System.out.println("\t\t\t\tWelcome to login page");
			System.out.println("Enter username");
			bs.nextLine();
			String uname=bs.nextLine();
			System.out.println("Enter password");
			int pwd=bs.nextInt();
			//we are creating a connection to database
			dao.dbconnection();
	        int res=dao.login(uname,pwd);
	        //login methods starts here based on below condition we get the output
	        if(res==0) {
	        	
	        	System.out.println("username/password is incorrect");
	             }
	        else if(res==-1) {
	        	
	        	System.out.println("unable to find the details");

	            }
            else{
	        	
	        	System.out.println("Login successful");
	        	System.out.println("press 1 for Deposit\n press 2 for withdraw\n press 3 for change password \n press 4 for delete account");
	        	int ops=bs.nextInt();
	        	
	        	switch(ops) {
	        	case 1->{
	        	//input of deposit
	        	System.out.println("Enter amount to deposit");
	        	int amt=bs.nextInt();
	        	int bal=dao.deposit(amt,res);
	        	System.out.println("Deposit successful\n Avaiable amt is"+bal);
	        	}
	        	case 2->{
	        		//input of withdraw
					System.out.println("Enter amount to withdraw");
					int amt=bs.nextInt();
					System.out.println("Confirm your password");
					int confmpwd=bs.nextInt();
					
					int availamt=dao.withdraw(amt, confmpwd, res);
					if(availamt==-1) {
						System.out.println("Low Balance");
					}
					else if(availamt==0) {
						System.out.println("Incorrect password");
					}
					else {
						System.out.println("Withdraw successful \n Available Amount is :"+availamt);
					}
	        	}
	        	case 3->{
					System.out.println("Enter current password");
					int currentpwd=bs.nextInt();
					System.out.println("Enter new password");
					int newpwd=bs.nextInt();
					
					int status=dao.changepwd(currentpwd, newpwd, res);
					if(status==1) {
						System.out.println("Password changed...");
					}
					else {
						System.out.println("Something went wrong");
					}
					
			}
			case 4->{
					System.out.println("Enter password to delete");
					int pass=bs.nextInt();
					int status=dao.deleteAccount(pass, res);
					if(status==1) {
						System.out.println("Your account is deleted\n byeeeeeeeeeeeeeee");
					}
					else {
						System.out.println("Something went wrong");
					}
					
					
					
					
					
			}
	        	}

		        }
			
			
		}
		
		
     }
        bs.close();

	}

}
