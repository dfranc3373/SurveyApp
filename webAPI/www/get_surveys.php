<?php

include("../class/sql.php");

$last_id = 0;

if(isset($_GET['last_id'])) {

	$last_id = $_GET['last_id'];

}

$sth = $db->prepare("SELECT * FROM `Surveys` WHERE `On` = '1'" . ($last_id != 0 ? " AND `SurveyID` > :LastID" : ""));

	$sth->setFetchMode(PDO::FETCH_OBJ);

	if($last_id == 0) {

		$sth->execute();

	} else {

		$sth->execute(array("LastID" => $last_id));

	}

if($sth->rowCount() == 0) {

		$arr = array('Success' => "true", 'Model' => array(""));

		echo json_encode($arr);

		exit();

} else {

	$result = $sth->fetchAll();

	$surveys = array();

	foreach($result as $survey) {

	$c = $db->prepare("SELECT * FROM `Companies` WHERE `CompanyID` = :CompanyID");

	$c->setFetchMode(PDO::FETCH_OBJ);

	$company = $c->fetch();

	$questions = array();

	$answers = array();

		$sth1 = $db->prepare("SELECT * FROM `Questions` WHERE `Survey` = :SurveyID");

		$sth1->setFetchMode(PDO::FETCH_OBJ);

		$sth1->execute(array("SurveyID" => $survey->SurveyID));

		$results = $sth1->fetchAll();

		foreach($results as $question) {

			$sth2 = $db->prepare("SELECT * FROM `QuestionAnswers` WHERE `Question` = :QuestionID");

			$sth2->setFetchMode(PDO::FETCH_OBJ);

			$sth2->execute(array("QuestionID" => $question->QuestionID));

			$r = $sth2->fetchAll();

			foreach($r as $answer) {

				array_push($answers, $answer);

			}

		$arr = array("QuestionID" => $question->QuestionID, "Survey" => $question->Title, "Description" => $question->Description, "Answers" => $answers);

		array_push($questions, $arr);

		}

	$sth3 = $db->prepare("SELECT * FROM `Coupons` WHERE `CouponID` = :Coupon");

	$sth3->setFetchMode(PDO::FETCH_OBJ);

	$sth3->execute(array("Coupon" => $survey->Coupon));

	$coupon = $sth3->fetch();

	$arr = array("SurveyID" => $survey->SurveyID, "Name" => $survey->Name, "Company" => $company, "DateCreated" => $survey->DateCreated, "AgeRange" => $survey->AgeRange, "GenderType" => $survey->GenderType, "Coupon" => $coupon, "Questions" => $questions);

	array_push($surveys, $arr);

	}

	$info = array('Success' => "true", 'Model' => $surveys);

	echo json_encode($info);

	exit();

}

			$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

?>
