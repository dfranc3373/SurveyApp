<?php

include("../class/sql.php");

$user_id = 0;

if(!isset($_GET['user_id'])) {

	$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

}

$user_id = $_GET['user_id'];

$coupons = array();

$sth = $db->prepare("SELECT * FROM `UserCoupons` WHERE `UserID` = :UserID");

$sth->setFetchMode(PDO::FETCH_OBJ);

$sth->execute(array("UserID" => $user_id));

if($sth->rowCount() == 0) {

	$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

} else {

	$result = $sth->fetchAll();

	$coupons = array();

	foreach($result as $userCoupon) {

		$c = $db->prepare("SELECT * FROM `Coupons` WHERE `CouponID` = :Coupon");

		$c->setFetchMode(PDO::FETCH_OBJ);

		$c->execute(array("Coupon" => $userCoupon->CouponID));

		$coupon = $c->fetch();

		$sth = $db->prepare("SELECT * FROM `Companies` WHERE `CompanyID` = :CompanyID");

		$sth->setFetchMode(PDO::FETCH_OBJ);

		$sth->execute(array("CompanyID" => $coupon->Company));

		$company = $sth->fetch();

		$coupon->Company = $company;

		array_push($coupons, $coupon);

	}

	$info = array('Success' => "true", 'Model' => $coupons);

	echo json_encode($info);

	exit();

}

			$arr = array('Success' => "false", 'Model' => array(""));

			echo json_encode($arr);

			exit();

?>
