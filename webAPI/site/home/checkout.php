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

require('../paypal_folder/bootstrap_ad.php');
use PayPal\Api\Amount;
use PayPal\Api\Details;
use PayPal\Api\Item;
use PayPal\Api\ItemList;
use PayPal\Api\CreditCard;
use PayPal\Api\Payer;
use PayPal\Api\Payment;
use PayPal\Api\FundingInstrument;
use PayPal\Api\Transaction;

$card = new CreditCard();
$card->setType($card_type)
	->setNumber($card_nunber)
	->setExpireMonth($expiration_month)
	->setExpireYear($expiration_year)
	->setFirstName($firstname)
	->setLastName($lastname);

// ### FundingInstrument
// A resource representing a Payer's funding instrument.
// For direct credit card payments, set the CreditCard
// field on this object.
$fi = new FundingInstrument();
$fi->setCreditCard($card);

// ### Payer
// A resource representing a Payer that funds a payment
// For direct credit card payments, set payment method
// to 'credit_card' and add an array of funding instruments.
$payer = new Payer();
$payer->setPaymentMethod("credit_card")
	->setFundingInstruments(array($fi));

// ### Itemized information
// (Optional) Lets you specify item wise
// information
$item1 = new Item();
$item1->setName('Invoice Payment - ' . $invoice_id)
	->setCurrency('USD')
	->setQuantity(1)
	->setPrice($cost);

$itemList = new ItemList();
$itemList->setItems(array($item1));

// ### Additional payment details
// Use this optional field to set additional
// payment information such as tax, shipping
// charges etc.
/*$details = new Details();
$details->setShipping('1.20')
	->setTax('1.30')
	->setSubtotal('17.50');*/

// ### Amount
// Lets you specify a payment amount.
// You can also specify additional details
// such as shipping, tax.
$amount = new Amount();
$amount->setCurrency("USD")
	->setTotal($cost);
	

// ### Transaction
// A transaction defines the contract of a
// payment - what is the payment for and who
// is fulfilling it. 
$transaction = new Transaction();
$transaction->setAmount($amount)
	->setItemList($itemList)
	->setDescription("Payment description");

// ### Payment
// A Payment Resource; create one using
// the above types and intent set to sale 'sale'
$payment = new Payment();
$payment->setIntent("sale")
	->setPayer($payer)
	->setTransactions(array($transaction));

// ### Create Payment
// Create a payment by calling the payment->create() method
// with a valid ApiContext (See bootstrap.php for more on `ApiContext`)
// The return object contains the state.
try {
	$payment->create($apiContext);
} catch (PayPal\Exception\PPConnectionException $ex) {

	echo $ex;
	
	$pin = json_decode($ex->getData());

	print_r($pin);
	
	if($pin->name == "VALIDATION_ERROR") {
		
		if($pin->details[0]->issue == "Value is invalid") {
			
			echo "Invalid Credit Card Number - Please fix the error and try again";
			
			exit();
			
		} else if($pin->details[0]->issue == "Invalid expiration") {
			
			echo "Expired Card - Please check the expiration date and try again";
			
			exit();
			
		} else {
			
			echo "Error processing card - Check information and try again";
			
			exit();
			
		}
		
	} else {
		
		echo "Error processing card - Check information and try again";
			
		exit();
		
	}
	
	/*echo "Exception: " . $ex->getMessage() . PHP_EOL;
	//var_dump($ex->getData());
	
	echo $pin->name;
	
	echo "-";
	
	echo $pin->details[0]->field;
	
	echo " ";
	
	echo $pin->details[0]->issue;
	
	echo $payment->getState();*/
	
	exit(1);
}

if($payment->getState() == "approved") {
	
	$payment_query = "UPDATE `Invoice` SET `Paid` = '1', `PaypalData` = :PaypalData";

	$sth = $db->prepare($payment_query);

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$good = $sth->execute(array("PaypalData" => $payment->getId()));

	if($good) {
	
		echo "good";
	
	} else {
	
		echo "Payment Processed, however there was an error. Contact support with Paypal ID number: " . $payment->getId() . " and Error: Checkout Error 011";
	
	}
	
}

/*<html>
<head>
	<title>Direct Credit card payments</title>
</head>
<body>
	<div>
		Created payment:
		<?php echo $payment->getId();?>
	</div>
	<pre><?php var_dump($payment->toArray()); echo $payment->getState(); echo $payment->getId(); ?></pre>
	<a href='../index.html'>Back</a>
</body>
</html>*/

?>
