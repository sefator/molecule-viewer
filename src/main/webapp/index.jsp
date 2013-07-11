<html>
<head>
<script type="text/javascript"
	src="js/jquery-1.10.2.min.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<link rel="stylesheet" href="css/jquery.fileupload-ui.css">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<title>Simple molecule viewer</title>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2" style="margin-top:40px">
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
