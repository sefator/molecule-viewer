<html>
<head>
<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js"></script>
<script
	src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<link rel="stylesheet" href="css/jquery.fileupload-ui.css">
<link
	href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css"
	rel="stylesheet">
<title>Simple molecule viewer</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2" style="margin-top: 40px">
				<span class="btn btn-success fileinput-button"> <i
					class="icon-plus icon-white"></i> <span>Select files...</span> <input
					id="fileupload" type="file" name="file" data-url="upload" multiple>
				</span>
				<ul id='molecules' class="nav nav-list">
				</ul>
			</div>
			<div class="span10">
				<h1>Simple molecule viewer</h1>
				<img alt="" src="">
			</div>
		</div>
	</div>



</body>
</html>
