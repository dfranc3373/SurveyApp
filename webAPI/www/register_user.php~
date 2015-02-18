<?php

include("../classes/sql.php");

if(!isset($_POST['email'])) {

	$arr = array('Success' => "false", 'UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => '');

	echo json_encode($arr);

	exit();

}

	if(isset($_POST['fb_login'])) {

		$sth = $db->prepare("SELECT * FROM `users` WHERE `Email` = :email", `Password` = :password);

		$sth->setFetchMode(PDO::FETCH_OBJ);

		$sth->execute(array("email" => $_POST["email"], "Password" => $_POST["fb_id"]));

		$results = $sth->fetch();

		if(count($results) == 1) {

			$token = md5(rand() . rand());

			$sth1 = $db->prepare("UPDATE `Users` SET `Token` = :token WHERE `Email` = :email");

			$sth1->execute(array("token" => $token, "email" => $_POST["email"]));

			$json = array('Success' => "true", 'UserID' => $results->UserID, 'Email' => $results->Email, 'FB' => 'true', 'Gender' => $results->Gender, 'Age_Range' => $results->Age_Range, 'Token' => $token);

			return json_encode($json);

			exit();

		} else {

			$arr = array('Success' => "false", 'UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => '');

			echo json_encode($arr);

			exit();

		}

	}

	$sth = $db->prepare("SELECT * FROM `users` WHERE `Email` = :email AND `Password` = :password");

		$sth->setFetchMode(PDO::FETCH_OBJ);

		$sth->execute(array("email" => $_POST["email"], "password" => $_POST["password"]));

		$results = $sth->fetch();

		if(count($results) == 1) {

			$token = md5(rand() . rand());

			$sth1 = $db->prepare("UPDATE `Users` SET `Token` = :token WHERE `Email` = :email");

			$sth1->execute(array("Email" => $token, "Email" => $_POST["email"]));

			$json = array('Success' => "true", 'UserID' => $results->UserID, 'Email' => $results->Email, 'FB' => 'false', 'Gender' => $results->Gender, 'Age_Range' => $results->Age_Range, 'Token' => $token);

			return json_encode($json);

			exit();

		} else {

			$arr = array('Success' => "false", 'UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => '');

			echo json_encode($arr);

			exit();

		}

?>
