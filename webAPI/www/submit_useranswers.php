<?php

include("../class/sql.php");

$user_id;

$question_answer;

$good = false;

if(!isset($_GET['user_id']) || !isset($_GET['question_answers'])) {

	$arr = array('Success' => "false", 'Model' => array(""));

	echo json_encode($arr);

	exit();

}

$user_id = $_GET['user_id'];

$question_answers = $_GET['question_answers'];

$json = json_decode($question_answers);

foreach($json as $answer) {

	$sth = $db->prepare("SELECT * FROM `UserAnswers` WHERE `User` = :UserID AND `QuestionAnswer` = :QuestionAnswer");

	$sth->execute(array("UserID" => $user_id, "QuestionAnswer" => $answer));

	if($sth->rowCount() == 1) {

		$good = true;

		continue;

	}

	$sth = $db->prepare("INSERT INTO `UserAnswers` (User, QuestionAnswer, DateAnswered) VALUES (:UserID, :QuestionAnswered, :DateAnswered)");

	if($sth->execute(array("UserID" => $user_id, "QuestionAnswered" => $answer, "DateAnswered" => (date("Y-m-d H:i:s"))))) {

		$good = true;

	} else {

		$good = false;

	}

}

	if($good) {

		$arr = array('Success' => "true", 'Model' => array(""));

			echo json_encode($arr);

			exit();

	} else {

		$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

	}


			$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

?>
