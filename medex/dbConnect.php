<?php

	define('HOST','localhost');
	define('USER','momotowf_medex');
	define('PASS','ador12345');
	define('DB','momotowf_medex');
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
