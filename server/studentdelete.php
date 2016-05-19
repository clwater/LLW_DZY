<?php
header("Content-type: text/html; charset=utf-8");
$con = mysql_connect("127.0.0.1","root","123456");
mysql_query("set names utf8");

if ($con) {
 mysql_select_db("llw", $con);

 mysql_query("SET NAMES UTF8");
 mysql_query("set character_set_client=utf8");
 mysql_query("set character_set_results=utf8");


  $id = $_GET['id'];


 mysql_query("DELETE FROM `student` WHERE  student_id='$id'");


 if (mysql_affected_rows()) {
   mysql_query("DELETE FROM `lesson` WHERE  student_id='$id'");
   mysql_query("DELETE FROM `login` WHERE  loginid='$id'");
  echo "{\"statu\":\"success\"}";
 }else {
  echo "{\"statu\":\"error\"}";
 }

}
mysql_close($con);
?>
