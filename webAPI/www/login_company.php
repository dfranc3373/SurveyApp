<?php

include("../class/sql.php");

if(!isset($_POST['email'])) {

	$arr = array('Success' => "false", 'Model' => array('Company_ID' => '', 'Name' => '', 'Email' => '', 'Approved' => '', 'Currently_Approved' => '', 'Token' => '', 'Logo' => ''));

	echo json_encode($arr);

	exit();

}

		$sth = $db->prepare("SELECT * FROM `Companies` WHERE `Email` = :email AND `Password` = :password");

		$sth->setFetchMode(PDO::FETCH_OBJ);

		$sth->execute(array("email" => $_POST["email"], "password" => $_POST["password"]));

		$results = $sth->fetch();

		if($sth->rowCount() == 1) {

			$token = md5(rand() . rand());

			$sth1 = $db->prepare("UPDATE `Companies` SET `Token` = :token WHERE `Email` = :email");

			$sth1->execute(array("token" => $token, "email" => $_POST["email"]));

			$json = array('Success' => "true", 'Model' => array('Company_ID' => $results->CompanyID, 'Name' => $results->Name, 'Email' => $results->Email, 'Approved' => ($results->Approved == 1 ? "true" : "false"), 'Currently_Approved' => ($results->Currently_Approved == 1 ? "true" : "false"), 'Token' => $token, 'Logo' => $results->Logo));

			echo json_encode($json);

			exit();

		} else {

			$arr = array('Success' => "false", 'Model' => array('Company_ID' => '', 'Name' => '', 'Email' => '', 'Approved' => '', 'Currently_Approved' => '', 'Token' => '', 'Logo' => ''));

			echo json_encode($arr);

			exit();

		}

?>
