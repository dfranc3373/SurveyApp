<?php

/*require_once 'google/appengine/api/cloud_storage/CloudStorageTools.php';
use google\appengine\api\cloud_storage\CloudStorageTools;

$options = [ 'gs_bucket_name' => 'survey-app-texastech.appspot.com/' ];

$upload_url = CloudStorageTools::createUploadUrl('add_coupon2', $options);*/

@session_start();

include("header.php");

?>

<div class="container" style="max-width: 500px;">

      <form class="form-signin" action="add_coupon2.php" method="post" enctype="multipart/form-data">

        <h2 class="form-signin-heading">Add Coupon</h2>
        <label for="title" class="sr-only">Title</label>
        <input type="text" id="tilte" class="form-control" placeholder="Title" required autofocus name="title">
        <label for="description" class="sr-only">Description</label>
        <textarea id="description" class="form-control" placeholder="Description" required name="description" rows="3"></textarea>
	<label for="code" class="sr-only">Code</label>
        <input type="text" id="code" class="form-control" placeholder="Code" required autofocus name="code">

	<!--<input type="checkbox" id="new_picture" name="new_picture"">Add a picture

	<br />

	<div id="picture_choose" style="display: none; padding: 10px;"><input type="file" name="picture" id="picture">
	<input type="hidden" name="MAX_FILE_SIZE" value="1000000" />
	</div>-->

	<?php if(isset($_GET['message'])) { ?><div style="color: #FF0000;"><?php echo urldecode($_GET['message']); ?></div> <?php } ?>

	<input type="checkbox" id="deleteOnLoad" name="deleteOnLoad""> Delete Coupon After Opening

        <button class="btn btn-lg btn-primary btn-block" type="submit">Add Coupon</button>
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
