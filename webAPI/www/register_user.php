<?php

include("../class/sql.php");

if(!isset($_POST['email'])) {

	$arr = array('Success' => "false", 'Model' => array('UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => ''));

	echo json_encode($arr);

	exit();

}

		$sth = $db->prepare("SELECT * FROM `Users` WHERE `Email` = :email AND `Password` = :password");

		$sth->setFetchMode(PDO::FETCH_OBJ);

		$sth->execute(array("email" => $_POST["email"], "password" => $_POST["password"]));

		if($sth->rowCount() == 0) {

			$token = md5(rand() . rand());

			$sth1 = $db->prepare("INSERT INTO `Users` (`Name`, `Email`, `Password`, `Facebook`,`Gender`, `Age_Range`,`Token`) VALUES(:name, :email, :password, :facebook, :gender, :age_range, :token)");

			$sth1->setFetchMode(PDO::FETCH_OBJ);

			$info_string = array("name" => $_POST["name"], "email" => $_POST["email"], "password" => $_POST["password"], "facebook" => (isset($_POST["FB"]) ? 1 : 0), "gender" => ($_POST["gender"] == "Male" ? 1 : 2), "age_range" => 1, "token" => $token);

			//print_r($info_string);

			if($sth1->execute($info_string)) {

				$sth = $db->prepare("SELECT * FROM `Users` WHERE `Email` = :email AND `Password` = :password");

				$sth->setFetchMode(PDO::FETCH_OBJ);

				$sth->execute(array("email" => $_POST["email"], "password" => $_POST["password"]));

				$results = $sth->fetch();

				$json = array('Success' => "true", 'Model' => array('UserID' => $results->UserID, 'Email' => $results->Email, 'FB' => 'false', 'Gender' => $results->Gender, 'Age_Range' => $results->Age_Range, 'Token' => $token));

				echo json_encode($json);

				exit();

			} else {

				$arr = array('Success' => "false", 'Model' => array('UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => ''));

				echo json_encode($arr);

				exit();

			}

		} else {

			$arr = array('Success' => "false", 'Model' => array('UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => ''));

			echo json_encode($arr);

			exit();

		}

?>
