package com.bankingproject;

import java.sql.*;
//To get connection for registration,login,withdraw,change password it acts as the controller in MVC(model,controller,view)communicates with database 

public class bankingdao {
	//registering the driver

	Connection con=null;
	public void dbconnection()throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","270819");
	} 
	
	
	public int registercustomer(customer c1) throws Exception {
		//registering the customer in database
		
		String query="insert into customer values(?,?,?,?)";
		PreparedStatement bs=con.prepareStatement(query);
		bs.setInt(1,c1.cusid);
		bs.setString(2, c1.cusname);
		bs.setInt(3, c1.cuspin);
		bs.setInt(4,c1.cusamt);
		int res=bs.executeUpdate();
		return res;
	}
	
	public int login(String uname,int pwd) throws Exception {
		String query="select * from customer where cusname='"+uname+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		if(rs.next()) {
			
			int password=rs.getInt(3);
			if(password==pwd) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
		}
			else {
				return -1;
			}
		}
		
	
	public int withdraw(int amt,int pwd,int cusid)throws Exception {
		//we are fetching  the user details based on customer id
				String 	query2="select  * from customer where cusid="+cusid;
				
				Statement st=con.createStatement();
				
				ResultSet rs=st.executeQuery(query2);
				rs.next();
				//we are extracting available amount
				int availamt=rs.getInt(4);
				if(pwd==rs.getInt(3))
				{
					//if available amount is greater than  required amount then only withdraw happens
					if(amt<availamt) {
						availamt-=amt;
						String query="update customer set cusamt="+availamt+" where cusid="+cusid;
						
						PreparedStatement pst=con.prepareStatement(query);
						pst.executeUpdate();
						return availamt;		
					}
					else {
						return -1;
					}
				}
				else {
					return 0;
				}
	}
	public int changepwd(int currentpwd,int newpwd, int cusid)throws Exception{
		//fetching user details based on customer id
		String 	query2="select  * from customer where cusid="+cusid;
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		//Giving condition for confirming the existing password
		if(currentpwd==rs.getInt(3)) {
			//Here we are Updating the new password in database
			String query="update customer set cuspin="+newpwd+" where cusid="+cusid;
			
				PreparedStatement pst=con.prepareStatement(query);
				pst.executeUpdate();
				return 1;
		}
		else {
			return 0;
		}
	}
	
	public int deleteAccount(int pwd, int cusid)throws Exception{
		//fetching user details based on customer id
		String 	query2="select  * from customer where cusid="+cusid;
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		//confirming password
		if(pwd==rs.getInt(3)) {
		
			//To delete the account of the customer
			String query="delete from customer where cusid="+cusid;
			
			PreparedStatement pst=con.prepareStatement(query);
				pst.executeUpdate();
			return 1;
		}
		else {
			return 0;
		}
	}

 
	public int deposit(int amt,int customerid) throws Exception {
		//fetching user details based on customer id
		String query2="select * from customer where cusid="+customerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query2);
        rs.next();
        //Extracting the account balance
        int bal=rs.getInt(4);
        //Updating the amount with balance
        amt+=bal;
        //We are storing the updated amount
		String query="update customer set cusamt ="+amt+" where cusid="+customerid;
		PreparedStatement pst=con.prepareStatement(query);
		//we are returing the updated amount
		pst.executeUpdate();
		
        return amt;
   }
}
