<?php

include("../class/sql.php");

$user_id;

$question_answer;

$good = false;

if(!isset($_POST['user_id']) || !isset($_POST['question_answers'])) {

	$arr = array('Success' => "false", 'Model' => array(""));

	echo json_encode($arr);

	exit();

}

if($_POST['user_id'] == 0) {

	$arr = array('Success' => "false", 'Model' => array(""));

	echo json_encode($arr);

	exit();

}

$user_id = $_POST['user_id'];

$question_answers = $_POST['question_answers'];

$json = json_decode($question_answers);

foreach($json as $answer) {

	$sth = $db->prepare("SELECT * FROM `UserAnswers` WHERE `User` = :UserID AND `QuestionAnswer` = :QuestionAnswer");

	$sth->execute(array("UserID" => $user_id, "QuestionAnswer" => $answer->QuestionAnswerID));

	if($sth->rowCount() == 1) {

		$good = true;

		continue;

	}

	$sth = $db->prepare("INSERT INTO `UserAnswers` (User, QuestionAnswer, DateAnswered) VALUES (:UserID, :QuestionAnswered, :DateAnswered)");

	if($sth->execute(array("UserID" => $user_id, "QuestionAnswered" => $answer->QuestionAnswerID, "DateAnswered" => (date("Y-m-d H:i:s"))))) {

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
