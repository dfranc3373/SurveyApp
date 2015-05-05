<?php

include("../class/sql.php");

$company_id = 0;

$user_id = 0;

if(isset($_POST['SurveyID'])) {

	$SurveyID = $_POST['SurveyID'];

	$sth = $db->prepare("SELECT `CouponCode` FROM `Coupons` WHERE `CouponID` = (SELECT `Coupon` FROM `Surveys` WHERE `SuvreyID` = :SurveyID)");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute(array("SurveyID" => $SurveyID));

	if($sth->rowCount() == 1) {

		$code = $sth->fecth();

		$arr = array('Success' => "true", 'Model' => $code->CouponCode);

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
