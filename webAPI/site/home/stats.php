<?php

        include("header.php");

	$sth = $db->prepare("SELECT * FROM `Surveys` WHERE `On` = '1'");

	$sth->setFetchMode(PDO::FETCH_OBJ);

	$sth->execute();

	$surveys = $sth->fetchAll();

?>

<div align="center">

Survey Stat <br />

<select onchange="showCharts(this)">

	<option>None</option>

	<?php foreach($surveys as $survey) { ?>

	<option value="<?php echo $survey->SurveyID; ?>"><?php echo $survey->Name; ?></option>

	<?php } ?>

</select>

<br />

<div id="charts"></div>

</center>

<?php include("../footer.php"); ?>

<script type="text/javascript" src="http://www.chartjs.org/assets/Chart.min.js"></script>

<script type="text/javascript">

// Get context with jQuery - using jQuery's .get() method.
//var ctx = $("#myChart").get(0).getContext("2d");
//var graph = $("#graph").get(0).getContext("2d");
// This will get the first returned node in the jQuery collection.

/*var data = {
    labels: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "<dic Sunday"],
    datasets: [
        {
            label: "My First dataset",
            fillColor: "rgba(220,220,220,0.2)",
            strokeColor: "rgba(220,220,220,1)",
            pointColor: "rgba(220,220,220,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(220,220,220,1)",
            data: [65, 59, 80, 81, 56, 55, 40]
        },
        {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.2)",
            strokeColor: "rgba(151,187,205,1)",
            pointColor: "rgba(151,187,205,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(151,187,205,1)",
            data: [28, 48, 40, 19, 86, 27, 90]
        }
    ]
};*/

var data = [
    {
        value: 300,
        color:"#F7464A",
        highlight: "#FF5A5E",
        label: "Red"
    },
    {
        value: 50,
        color: "#46BFBD",
        highlight: "#5AD3D1",
        label: "Green"
    },
    {
        value: 100,
        color: "#FDB45C",
        highlight: "#FFC870",
        label: "Yellow"
    }
]

function showCharts(survey_id) {

        $("#charts").html("");

        var json = "";

	$.post("../../www/get_results.php?survey_id=" + survey_id.value, function(result) {

                json = JSON.parse(result);

                for (var id in json.Model) {

               if (json.Model.hasOwnProperty(id)) {

                     var data = [];

                for (var answer in json.Model[id].Answers) {

               if (json.Model[id].Answers.hasOwnProperty(answer)) {

                     data.push({
                        value: json.Model[id].Answers[answer].Clicks,
                        color: '#'+Math.floor(Math.random()*16777215).toString(16),
                        highlight: '#'+Math.floor(Math.random()*16777215).toString(16),
                        label: json.Model[id].Answers[answer].Answer
                    });

                }

                }

                     $("#charts").append("<div>" + json.Model[id].Question + "<br>");

                     $("#charts").append("<canvas id='myChart" + id + "' width='400' height='400'></canvas> <canvas id='graph' width='400' height='400'></canvas></div>");

	var ctx = $("#myChart" + id).get(0).getContext("2d");

	var myDoughnutChart = new Chart(ctx).Doughnut(data, null);

               }

        }

	});

        /*$("#charts").html("");

        $("#charts").append("<canvas id='myChart' width='400' height='400'></canvas> <canvas id='graph' width='400' height='400'></canvas>");

	var ctx = $("#myChart").get(0).getContext("2d");

	var myDoughnutChart = new Chart(ctx).Doughnut(data, null);*/

}

</script>
