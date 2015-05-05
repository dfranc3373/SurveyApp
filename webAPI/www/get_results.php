<?php

include("../class/sql.php");

$company_id = 0;

$user_id = 0;

$questions = array();

if(isset($_GET['survey_id'])) {

	$survey_id = $_GET['survey_id'];

	$sth = $db->prepare("SELECT * FROM `Questions` WHERE `Survey` = :SurveyID");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute(array("SurveyID" => $survey_id));

	if($sth->rowCount() == 0) {

		$arr = array('Success' => "true", 'Model' => array(""));

		echo json_encode($arr);

		exit();

	} else {

		$results = $sth->fetchAll();

		$question_answers = array();

		foreach($results as $question) {

			$sth = $db->prepare("SELECT * FROM `QuestionAnswers` WHERE `Question` = :QuestionID");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute(array("QuestionID" => $question->QuestionID));

	$results_answers = $sth->fetchAll();

			foreach($results_answers as $answer) {

				$sth = $db->prepare("SELECT * FROM `UserAnswers` WHERE `QuestionAnswer` = :QuestionAnswer");

				$sth->setFetchMode(PDO::FETCH_OBJ);

				$sth->execute(array("QuestionAnswer" => $answer->QuestionAnswerID));

				$results_answers = $sth->rowCount();

				$answer_array = array('Answer' => $answer->Answer, 'Clicks' => $results_answers);

				array_push($question_answers, $answer_array);

			}

		$resultclick = array('Question' => $question->Title, 'Answers' => $question_answers);

		array_push($questions, $resultclick);

		}//end foreach

		$result = array('Success' => "true", 'Model' => $questions);

		echo json_encode($result);

		exit();

	}//end if

}//end if

			$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

?>
