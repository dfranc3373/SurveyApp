<?php

include("../class/sql.php");

 = 0;

$user_id = 0;

    $sth = $db->prepare("SELECT * FROM `Surveys` WHERE `active` = '1'");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute();

if($sth->rowCount() == 0) {
    
    $arr = array('Success' => "false", 'Model' => "");
    
    echo json_encode($arr);
    
    exit();
    
} else {
    
    $survey_results = $sth->fetchAll();
    
    $surveys = array();

    foreach($survey_results as $survey) {

			$survey_array = array('SurveyID' => $survey->SurveyID, 'Title' => $survey->Title, 'Description' => $survey->Description, 'Coupon' => $survey->CouponCode, 'CouponImage' => $survey->CouponImage, 'DeleteOnLoad' => ($survey->DeleteOnLoad == 1 ? "true" : "false"), 'Company' => $survey->Company);

			array_push($coupons, $survey_array);

		}//end foreach

		$result = array('Success' => "true", 'Model' => $coupons);

		echo json_encode($result);

		exit();
    
}

if(isset($_POST['company_id'])) {

	$company_id = $_POST['company_id'];

	$sth = $db->prepare("SELECT * FROM `Coupons` WHERE `Company` = :CompanyID");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute(array("CompanyID" => $company_id));

	if($sth->rowCount() == 0) {

		$arr = array('Success' => "true", 'Model' => array(""));

		echo json_encode($arr);

		exit();

	} else {

		$results = $sth->fetchAll();

		$coupons = array();

		foreach($results as $survey) {

			$survey_array = array('CouponID' => $survey->CouponID, 'Title' => $survey->Title, 'Description' => $survey->Description, 'Coupon' => $survey->CouponCode, 'CouponImage' => $survey->CouponImage, 'DeleteOnLoad' => ($survey->DeleteOnLoad == 1 ? "true" : "false"), 'Company' => $survey->Company);

			array_push($coupons, $survey_array);

		}//end foreach

		$result = array('Success' => "true", 'Model' => $coupons);

		echo json_encode($result);

		exit();

	}//end if

}//end if

if(isset($_POST['user_id'])) {

	$user_id = $_POST['user_id'];

	$sth = $db->prepare("SELECT * FROM `UserCoupons` WHERE `UserID` = :UserID");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute(array("UserID" => $user_id));

	if($sth->rowCount() == 0) {

		$arr = array('Success' => "true", 'Model' => array(""));

		echo json_encode($arr);

		exit();

	} else {

		$results = $sth->fetchAll();

		$coupons = array();

		foreach($results as $user_survey) {

			$sth1 = $db->prepare("SELECT * FROM `Coupons` WHERE `CouponID` = :CouponID");

			$sth1->setFetchMode(PDO::FETCH_OBJ);

			$sth1->execute(array("CouponID" => $user_survey->CouponID));

			if($sth1->rowCount() == 0) {

				$arr = array('Success' => "false", 'Model' => array('UserID' => '', 'Email' => '', 'FB' => '', 'Gender' => '', 'Age_Range' => '', 'Token' => ''));

				echo json_encode($arr);

				exit();

			} else {

				$coupons = array();

				$surveys = $sth1->fetchAll();

				foreach($surveys as $survey) {

					$survey_array = array('CouponID' => $survey->CouponID, 'Title' => $survey->Title, 'Description' => $survey->Description, 'Coupon' => $survey->CouponCode, 'CouponImage' => $survey->CouponImage, 'DeleteOnLoad' => ($survey->DeleteOnLoad == 1 ? "true" : "false"), 'Company' => $survey->Company);

					array_push($coupons, $survey_array);

				}//end foreach

				$result = array('Success' => "true", 'Model' => $coupons);

				echo json_encode($result);

				exit();

			}//end if

		}//end foreach

	}//end if

}//end if

			$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

?>
