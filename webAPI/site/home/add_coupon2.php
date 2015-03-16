<?php

error_reporting(E_ALL);
ini_set('display_errors', '1');

@session_start();

$file = "../";

include($file . "include.php");

if(!isset($_POST['title']) || !isset($_POST['description'])) {

	header("Location: add_coupon.php?message=" . urlencode('Make sure you put all the info that is needed'));

	exit();

}

/*($target_path = "../coupons/";

$img_src = "images/" . $_FILES['picture']['name'];

$target_path = $target_path . basename( $_FILES['picture']['name']); 

if(move_uploaded_file($_FILES['picture']['tmp_name'],  $target_path)) { }*/

	$sth = $db->prepare("INSERT INTO `Coupons` (`Title`, `Description`, `CouponCode`, `CouponImage`, `DeleteOnLoad`, `Company`) VALUES (:title, :description, :couponCode, :couponImage, :deleteOnLoad, :company);");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	if($sth->execute(array("title" => $_POST['title'], "description" => $_POST['description'], "couponCode" => $_POST['code'], "couponImage" => "", "deleteOnLoad" => (isset($_POST['deleteOnLoad']) ? 1 : 0), "company" => $_SESSION['company_id']))) {

		header("Location: add_coupon.php?message=" . urlencode('News has been added'));

		exit();

	} else {

echo $sth->errorInfo();

exit();

		header("Location: add_coupon.php?message=" . urlencode('Error news has not been added'));

		exit();

	}

	header("Location: add_coupon.php");

?>
