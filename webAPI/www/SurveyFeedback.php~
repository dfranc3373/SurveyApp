<?php

include("../class/sql.php");

if(!isset($_POST['SurveyID']) || !isset($_POST['rating'])) {

	$arr = array('Success' => "false", 'Model' => array(""));

	echo json_encode($arr);

	exit();

}

$SurveyID = $_POST['SurveyID'];

$rating = $_POST['rating'];

$sth = $db->prepare("SELECT * FROM `Surveys` WHERE `SurveyID` = :SurveyID");

$sth->execute(array("SurveyID" => $SurveyID));

if($sth->rowCount() == 1) {

	$sth = $db->prepare("INSERT INTO `SurveyRatings` (SurveyID, Rating, DateRated) VALUES (:SurveyID, :rating, :DateAnswered)");

	if($sth->execute(array("SurveyID" => $SurveyID, "rating" => $rating, "DateAnswered" => (date("Y-m-d H:i:s"))))) {

		$arr = array('Success' => "true", 'Model' => array(""));

			echo json_encode($arr);

			exit();

	} else {

		$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

	}

}

	$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();


?>
