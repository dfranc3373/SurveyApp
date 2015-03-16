<?php

@session_start();

	$file = "../";

	require_once($file . "include.php");

	if(!isset($_SESSION['logged_in'])) {

		header("Location: ../index.php");

	}

	if(!$_SESSION['logged_in']) {

		header("Location: ../index.php");

	}

	//$sth_classes = $db->query("SELECT * FROM `classes` ORDER BY `name` DESC");

	//$sth_classes->setFetchMode(PDO::FETCH_OBJ);

	//$classes = $sth_classes->fetchAll();

?>

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta content="" name="description">
    <meta content="" name="author">
    <link href="<?php echo $file; ?>favicon.ico" rel="icon">

    <title>Survey App - <?php echo $_SESSION['company_name']; ?></title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="<?php echo $file; ?>css/bootstrap.min.css">

	<style type="text/css">

		body {
		  padding-top: 50px;
		  padding-bottom: 20px;
		}

	</style>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="index.php" class="navbar-brand"><img width="100px" style="margin-top: -9px;" src="<?php echo $file; ?>logo.png"></a>
        </div>
        <div class="navbar-collapse collapse" id="navbar" aria-expanded="false" style="height: 1px;">
<ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="index.php">Home</a></li>
            <li><a href="logout.php">Logout</a></li>
          </ul>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
