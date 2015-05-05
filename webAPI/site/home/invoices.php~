<?php

include_once('header.php');

$sth = $db->prepare("SELECT * FROM `Invoices` WHERE `Company` = :CompanyID AND `paid` = '0'");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute(array("CompanyID" => $_SESSION['company_id']));

	$invoices = $sth->fetchAll();

?>

<div style="background-color: #FFFFFF;" class="jumbotron" align="center">

<h1>Invoices</h1>

<div style="color: #0000FF;"><?php echo @$_GET['message']; ?></div>

      <div style="color: #000000;" class="container" align="center">
	
	<?php foreach($invoices as $invoice) { ?>

	<h3>Invoice <?php echo $invoice->InvoiceID; ?> for $<?php echo $invoice->Amount; ?> - <a href="#" onclick="payInvoice(<?php echo $invoice->InvoiceID; ?>);">Pay</a></h3>

	<div style="width: 100%; position: inherit;" id="pay">

<form action="paypal.php" method="POST">

<input type="hidden" value="<?php echo $invoice->InvoiceID; ?>" id="invoice_id" name="invoice_id">
			
			<h2>Amount: <?php echo $invoice->Amount; ?></h2>
			
			<h4>Card Info:</h4>
	
			<br />
			
			<div class='row'>
				
				<div class='col-sm-4'></div>
				
				<div class='col-sm-2'>
					
					<input type='text' name='firstname' class='form-control' placeholder='First Name' style='max-width: 300px;' id='firstname'>
					
				</div>
			
				<div class='col-sm-2'>
					
					<input type='text' name='lastname' class='form-control' placeholder='Last Name' style='max-width: 300px;' id='lastname'>
					
				</div>
				
				<div class='col-sm-4'></div>
			
			</div>
			
			<br />
			
			<select name='card_type' id='card_type'>
			
				<option value='visa'>Visa</option>
				
				<option value='mastercard'>Mastercard</option>
				
				<option value='discover'>Discover</option>
			
			</select>
			
			<br />
			
			<br />
			
			<input type='text' name='cardnumber' id='cardnumber' class='form-control' placeholder='Card Number' style='max-width: 300px;'>
			
			<br />
			
			<br />
			
			Experation Date: <br />
			
			<select name='expiration_month' id='expiration_month'>
			
				<option value='01'>01</option>
				
				<option value='02'>02</option>
				
				<option value='03'>03</option>
				
				<option value='04'>04</option>
				
				<option value='05'>05</option>
				
				<option value='06'>06</option>
				
				<option value='07'>07</option>
				
				<option value='08'>08</option>
				
				<option value='09'>09</option>
				
				<option value='09'>09</option>
				
				<option value='10'>10</option>
				
				<option value='11'>11</option>
				
				<option value='12'>12</option>
			
			</select>
			
			<select name='expiration_year' id='expiration_year'>
			
				<option value='2013'>2013</option>
				
				<option value='2014'>2014</option>
				
				<option value='2015'>2015</option>
				
				<option value='2016'>2016</option>
				
				<option value='2017'>2017</option>
				
				<option value='2018'>2018</option>
				
				<option value='2019'>2019</option>
				
				<option value='2020'>2020</option>
				
				<option value='2021'>2021</option>
				
				<option value='2022'>2022</option>
				
				<option value='2023'>2023</option>
				
				<option value='2024'>2024</option>
				
				<option value='2025'>2025</option>
			
			</select>
			
			<br />
			
			<br />
			
			<input type="submit" class="btn btn-success" value="Process Payment">

</form>
			
		</div>

	<?php

	} ?>

      </div>
    </div>

    <div class="container" style="margin-top: -10px;">

      </div>
  </div>

<?php include("../footer.php"); ?>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
