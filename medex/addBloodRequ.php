<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values
		$name = $_POST['name'];
		$blood_group = $_POST['blood_group'];
		$place = $_POST['place'];
		$phone_number = $_POST['phone_number'];
		$date = $_POST['date'];
	
		
		//Creating an sql query
		$sql = "INSERT INTO tbl_blood_request (name,blood_group,place,phone_number,date) VALUES ('$name','$blood_group','$place','$phone_number','$date')";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'Request Sent to All';
		}else{
			echo 'Server Error!';
		}
		
		//Closing the database 
		mysqli_close($con);
	}