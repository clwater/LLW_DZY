<?php
header("Content-type: text/html; charset=utf-8");
$con = mysql_connect("127.0.0.1","root","123456");
mysql_query("set names utf8");

if ($con) {
 mysql_select_db("llw", $con);

 mysql_query("SET NAMES UTF8");
 mysql_query("set character_set_client=utf8");
 mysql_query("set character_set_results=utf8");

  $name = $_GET['name'];
  $id = $_GET['id'];
  $class = $_GET['class'];


 mysql_query("UPDATE student SET student_name = '$name' , student_class='$class'  WHERE student_id = '$id'");

 if (mysql_affected_rows()) {
   mysql_query("UPDATE lesson SET student_name = '$name'   WHERE student_id = '$id'");
  echo "{\"statu\":\"success\"}";
 }else {
  echo "{\"statu\":\"error\"}";
 }

}
mysql_close($con);
?>
