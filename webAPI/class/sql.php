<?php

error_reporting(E_ALL);
ini_set('display_errors', '1');

$db = new pdo("mysql:unix_socket=/cloudsql/survey-app-texastech:surveyapp;dbname=SurveyApp", 'root', '');
//$db = new pdo("mysql:host=localhost;dbname=SurveyApp", 'root', '');

?>
