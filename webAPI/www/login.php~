<?php

include("../class/sql.php");

if(!isset($_POST['email'])) {

	$arr = array('Success' => "false", 'Model' => array('UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => ''));

	echo json_encode($arr);

	exit();

}

	if(isset($_POST['fb_login'])) {

		$sth = $db->prepare("SELECT * FROM `Users` WHERE `Email` = :email AND `Password` = :password");

		$sth->setFetchMode(PDO::FETCH_OBJ);

		$sth->execute(array("email" => $_POST["email"], "password" => $_POST["password"]));

		$results = $sth->fetch();

		if($sth->rowCount() == 1) {

			$token = md5(rand() . rand());

			$sth1 = $db->prepare("UPDATE `Users` SET `Token` = :token WHERE `Email` = :email");

			$sth1->execute(array("token" => $token, "email" => $_POST["email"]));

			$json = array('Success' => "true", 'Model' => array('UserID' => $results->UserID, 'Email' => $results->Email, 'FB' => 'true', 'Gender' => $results->Gender, 'Age_Range' => $results->Age_Range, 'Token' => $token));

			echo json_encode($json);

			exit();

		} else {

			$arr = array('Success' => "false", 'Model' => array('UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => ''));

			echo json_encode($arr);

			exit();

		}

	}

		$sth0 = $db->prepare("SELECT * FROM `Users` WHERE `Email` = :email AND `Password` = :password");

		$sth0->setFetchMode(PDO::FETCH_OBJ);

		$sth0->execute(array("email" => $_POST["email"], "password" => $_POST["password"]));

		$results = $sth0->fetch();

		if($sth0->rowCount() == 1) {

			$token = md5(rand() . rand());

			$sth1 = $db->prepare("UPDATE `Users` SET `Token` = :token WHERE `Email` = :email");

			$sth1->setFetchMode(PDO::FETCH_OBJ);

			$sth1->execute(array("token" => $token, "email" => $_POST["email"]));

			$json = array('Success' => "true", 'Model' => array('UserID' => $results->UserID, 'Email' => $results->Email, 'FB' => 'false', 'Gender' => $results->Gender, 'Age_Range' => $results->Age_Range, 'Token' => $token));

			echo json_encode($json);

			exit();

		} else {

			$arr = array('Success' => "false", 'Model' => array('UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => ''));

			echo json_encode($arr);

			exit();

		}

?>
