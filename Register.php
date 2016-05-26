<?php
    private String DBHost = "localhost";
	private String DBUsername = "root";
	private String DBPassword = "";
	private String DBURL = "tradex";
    $con = mysqli_connect(DBHost, DBUsername, DBPassword, DBURL);
    
    $email = $_POST["email"];
    $firstName = $_POST["firstName"];
    $lastName = $_POST["lastName"];
    $password = $_POST["password"];
	$dob = $_POST["dob"];
	$country = $_POST["country"];
	$city = $_POST["city"];
	$phoneNo = $_POST["phoneNo"];
	$skypeNo = $_POST["skypeNo"];
	
    function registerUser()
	{
        global $connect, $firstName, $lastName, $password, $dob, $country, $city, $phoneNo, $skypeNo;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO users (email, firstName, lastName, password, dateOfBirth, country, city, phoneNo, skypeNo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "ssssdssii", $firstName, $lastName, $passwordHash, $dob, $country, $city, $phoneNo, $skypeNo);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
	
    function userAvailable()
	{
        global $connect, $username;
        $statement = mysqli_prepare($connect, "SELECT * FROM user WHERE email = ? AND password = ?"); 
        mysqli_stmt_bind_param($statement, "ss", $email, $password);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        } else {
            return false; 
        }
    }
	
    $response = array();
    $response["success"] = false;  
    if (userAvailable())
	{
        registerUser();
        $response["success"] = true;  
    }
    echo json_encode($response);
?>