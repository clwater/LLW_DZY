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

 $result = mysql_query("INSERT INTO student VALUES ($name, $id, $class)");



 if ($result > 0) {
  mysql_query("INSERT INTO `lesson`(`student_id`, `student_name`) VALUES ($id, $name)");
  echo "{\"statu\":\"success\"}";
 }else {
  echo "{\"statu\":\"error\"}";
 }




}
mysql_close($con);
?>
