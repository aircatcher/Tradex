<?php
	private String DBHost = "localhost";
	private String DBUsername = "root";
	private String DBPassword = "";
	private String DBURL = "tradex";
    $con = mysqli_connect(DBHost, DBUsername, DBPassword, DBURL);
    
    $email = $_POST["email"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM users WHERE email = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $email, $password);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colID, $colEmail, $colFirstName, $colLastName, $colFullName $colPassword, $colDOB, $colCountry, $colCity, $colPhoneNo, $colSkypeNo, $colType);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password, $colPassword)) {
			$statement = mysqli_prepare($con, "SELECT firstName, lastName, CONCAT_WS(' ', firstName, lastName) AS fullName FROM users");
			mysqli_stmt_bind_param($statement, "ss", $email, $password);
			mysqli_stmt_execute($statement);
			
            $response["success"] = true;
			$response["fullName"] = $colFullName;
            $response["email"] = $colEmail;
        }
    }
    echo json_encode($response);
?>