<?php

@session_start();

$file = "";

include("include.php");

if(!isset($_POST['email']) || !isset($_POST['password'])) {

	header("Location: index.php");

	exit();

}

$postdata = http_build_query(
    array(
        'email' => $_POST['email'],
        'password' => $_POST['password']
    )
);

$opts = array('http' =>
    array(
        'method'  => 'POST',
        'header'  => 'Content-type: application/x-www-form-urlencoded',
        'content' => $postdata
    )
);

$context  = stream_context_create($opts);

$result = file_get_contents($url . 'www/login_company.php', false, $context);

$class = json_decode($result);

if($class->Success == "true") {

	$_SESSION['token'] = $class->Model->Token;

	$_SESSION['logged_in'] = "true";

	$_SESSION['company_name'] = $class->Model->Name;

	$_SESSION['company_id'] = $class->Model->Company_ID;

	header("Location: home/index.php");

	exit();

} else {

	header("Location: login.php?message=" . urlencode("Wrong password"));

}

/*if($count == 1) {

	$_SESSION['logged_in'] = "true";

	$_SESSION['username'] = $_POST['email'];

	header("Location: home");

	exit();

}

header("Location: login.php?message=" . urlencode("Wrong password"));*/

?>
