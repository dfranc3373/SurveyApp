<?php

include("header.php");

//error_reporting(E_ALL);
//ini_set('display_errors', '0');

if(!isset($_POST['firstname']) || !isset($_POST['lastname']) || !isset($_POST['card_type']) || !isset($_POST['cardnumber']) || !isset($_POST['expiration_month']) || !isset($_POST['expiration_year']) || !isset($_POST['invoice_id'])) {
	
	echo "Not all data sent. Please fill out the form and try again";
	
	//print_r($_POST);
	
	exit();
	
}

$firstname = $_POST['firstname'];

$lastname = $_POST['lastname'];

$card_type = $_POST['card_type'];

$card_nunber = str_replace(" ", "", str_replace("-", "", $_POST['cardnumber']));

$expiration_month = $_POST['expiration_month'];

$expiration_year = $_POST['expiration_year'];

$invoice_id = $_POST['invoice_id'];

$cost = "0.00";

$sth = $db->prepare("SELECT `Amount` FROM `Invoices` WHERE `InvoiceID` = :InvoiceID");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute(array("InvoiceID" => $_POST['invoice_id']));

	$invoice = $sth->fetch();

	$cost = $invoice->Amount;

if(strpos($cost,'.00') === false) {
	
	$cost = $cost . ".00";
	
}

if(strpos($cost,'$') !== false) {
	
	$cost = substr($cost, 1);
	
}

$sth = $db->prepare("UPDATE `Invoices` SET `paid` = '1' WHERE `InvoiceID` = :InvoiceID");

if($sth->execute(array("InvoiceID" => $invoice_id))) {

	header("Location: invoices.php?message=Good");

} else {

	header("Location: invoices.php?message=Good");

	}

?>
