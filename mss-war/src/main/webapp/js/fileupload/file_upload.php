
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<style type="text/css">
body
{
	font:12px Verdana, Arial, Helvetica, sans-serif;
	
}
</style>
<body">
<?php
echo "Post ID :".$_GET['post_id'];
echo "<br />";
if($_POST)
{
	echo "<span style='color:red; font-weight:bold'>File Uploaded Details </span><br />";
	print_r($_FILES);
}
?>
<p align="center">
<form action="" method="post" name="form1" enctype="multipart/form-data">
<input name="file_upload" type="file">&nbsp;&nbsp;&nbsp;&nbsp;<input name="submit" value="upload" type="submit">
</form>
</p>
<div align="center">&nbsp;</div>
<div align="center"><a href="#" onClick="self.parent.tb_remove();" ><strong>Close This box</strong></a></div>
</body>



</html>
