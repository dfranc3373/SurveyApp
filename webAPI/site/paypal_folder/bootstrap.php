<?php

/*
 * Sample bootstrap file.
 */

// Include the composer autoloader
if(!file_exists(__DIR__ .'/vendor/autoload.php')) {
	echo "The 'vendor' folder is missing. You must run 'composer update --no-dev' to resolve application dependencies.\nPlease see the README for more information.\n";
	exit(1);
}


require __DIR__ . '/vendor/autoload.php';
require __DIR__ . '/common.php';

use PayPal\Rest\ApiContext;
use PayPal\Auth\OAuthTokenCredential;

$apiContext = getApiContext();


/**
 * Helper method for getting an APIContext for all calls
 *
 * @return PayPal\Rest\ApiContext
 */
function getApiContext() {
	
	// ### Api context
	// Use an ApiContext object to authenticate 
	// API calls. The clientId and clientSecret for the 
	// OAuthTokenCredential class can be retrieved from 
	// developer.paypal.com

	$apiContext = new ApiContext(
		new OAuthTokenCredential(
			'AV4ASelWEbOqEIXb-F0XcTa1C01WKN2f-1OkXBXhzK-Y3DepsnI6Mtqu16bvcri7qhOXQO9eYYuaKK6t',
			'EIexMVYg0GIcsmymUc9qf1Ri5tFmE1Jjg6Hh2Bhh0u5T_H0W9x5YhM_PtlfHFaQ1DciHvoOz6p0sB_AN'
		)
	);



	// #### SDK configuration
	
	// Comment this line out and uncomment the PP_CONFIG_PATH
	// 'define' block if you want to use static file 
	// based configuration
	
	$apiContext->setConfig(
		array(
			'mode' => 'live',
			'http.ConnectionTimeOut' => 30,
			'log.LogEnabled' => false,
			'log.FileName' => '../../../../paypal_folder/PayPal.log',
			'log.LogLevel' => 'FINE'
		)
	);
	
	/*$apiContext->setConfig(
		array(
			'mode' => 'sandbox',
			'http.ConnectionTimeOut' => 30,
			'log.LogEnabled' => true,
			'log.FileName' => '../../../../paypal_folder/PayPal.log',
			'log.LogLevel' => 'FINE'
		)
	);*/
	
	/*
	// Register the sdk_config.ini file in current directory
	// as the configuration source.
	if(!defined("PP_CONFIG_PATH")) {
		define("PP_CONFIG_PATH", __DIR__);
	}
	*/

	return $apiContext;
}
