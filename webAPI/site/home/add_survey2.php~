<?php

//error_reporting(E_ALL);
//ini_set('display_errors', '1');

@session_start();

$file = "../";

include($file . "include.php");

if(!isset($_POST['title']) || !isset($_POST['description'])) {

	header("Location: add_survey.php?message=" . urlencode('Make sure you put all the info that is needed'));

	exit();

}

/*($target_path = "../coupons/";

$img_src = "images/" . $_FILES['picture']['name'];

$target_path = $target_path . basename( $_FILES['picture']['name']); 

if(move_uploaded_file($_FILES['picture']['tmp_name'],  $target_path)) { }*/

	$sth = $db->prepare("INSERT INTO `Surveys` (`Name`, `Company`, `DateCreated`, `On`, `AgeRange`, `GenderType`, `Coupon`) VALUES (:Name, :Company, :DateCreated, :On, :AgeRange, :GenderType, :Coupon);");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	//echo "Start";

	if($sth->execute(array("Name" => $_POST['title'], "Company" => $_SESSION['company_id'], "DateCreated" => (date("Y-m-d H:i:s")), "On" => "1", "AgeRange" => "1", "GenderType" => "1", "Coupon" => $_POST['coupon']))) {

		$survey = $db->lastInsertId();

		$sth1 = $db->prepare("INSERT INTO `Questions` (`Survey`, `Title`, `Description`) VALUES (:Survey, :Title, :Description);");

		if($sth1->execute(array("Survey" => $survey, "Title" => $_POST['title'], "Description" => $_POST['description']))) {

			$question = $db->lastInsertId();

			foreach($_POST['answer'] as $a)
			{

				$sth2 = $db->prepare("INSERT INTO `QuestionAnswers` (`Question`, `Answer`) VALUES (:Question, :Answer);");

				$sth2->execute(array("Question" => $question, "Answer" => $a));

				header("Location: add_survey.php?message=" . urlencode('Survey has been added'));

				exit();

			}

		}

		header("Location: add_survey.php?message=" . urlencode('Error Survey not added'));

		exit();

	} else {

		print_r($sth->errorInfo());

	}


?>
