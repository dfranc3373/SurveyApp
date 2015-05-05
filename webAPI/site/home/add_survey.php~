<?php

/*require_once 'google/appengine/api/cloud_storage/CloudStorageTools.php';
use google\appengine\api\cloud_storage\CloudStorageTools;

$options = [ 'gs_bucket_name' => 'survey-app-texastech.appspot.com/' ];

$upload_url = CloudStorageTools::createUploadUrl('add_coupon2', $options);*/

@session_start();

include("header.php");

$postdata = http_build_query(
    array(
        'company_id' => $_SESSION['company_id']
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

$coupons = json_decode("");

$result = file_get_contents($url . 'www/get_coupons.php', false, $context);

$json = json_decode($result);

if($json->Success == "true") {

	$coupons = json_decode(json_encode($json->Model));

}

?>

<div class="container" style="max-width: 500px;">

      <form class="form-signin" action="add_survey2.php" method="post" enctype="multipart/form-data">

        <h2 class="form-signin-heading">Add Survey</h2>

	Coupon:
	
	<br />

	<select id="coupon" name="coupon">

		<option value="0" >Select a Coupon</option>

		<?php

			foreach($coupons as $coupon) {


		?>

		<option value="<?php echo $coupon->CouponID; ?>"><?php echo $coupon->Title; ?></option>

		<?php

			}


		?>

	</select>

        <label for="title" class="sr-only">Title</label>
        <input type="text" id="tilte" class="form-control" placeholder="Title" required autofocus name="title">
        <label for="description" class="sr-only">Description</label>
        <textarea id="description" class="form-control" placeholder="Description" required name="description" rows="3"></textarea>

	<?php if(isset($_GET['message'])) { ?><div style="color: #FF0000;"><?php echo urldecode($_GET['message']); ?></div> <?php } ?>

<br />

	<input type="text" id="answer1" class="form-control" placeholder="Answer 1" required autofocus name="answer[]">
        <label for="description" class="sr-only">Answer 1</label>

	<input type="text" id="answer2" class="form-control" placeholder="Answer 2" required autofocus name="answer[]">
        <label for="description" class="sr-only">Answer 2</label>

	<input type="text" id="answer3" class="form-control" placeholder="Answer 3" required autofocus name="answer[]">
        <label for="description" class="sr-only">Answer 3</label>

	<input type="text" id="answer4" class="form-control" placeholder="Answer 4" required autofocus name="answer[]">
        <label for="description" class="sr-only">Answer 4</label>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Add Survey</button>
      </form>

    </div>

<?php include("../footer.php"); ?>

<script type="text/javascript">

	$("#new_picture").change(function() {

		if(this.checked) {

			$('#picture').prop('required', true);

			$("#picture_choose").css("display", "inline");

		} else {

			$('#picture').prop('required', false);

			$("#picture_choose").css("display", "none");

		}

	});

</script>
