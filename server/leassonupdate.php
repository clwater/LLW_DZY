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
  $att = $_GET['att'];
  $statu = $_GET['statu'];
  $work = $_GET['work'];
  $test = $_GET['test'];
  $score = $_GET['score'];




 mysql_query("UPDATE lesson SET attendance = '$att' , lessonstatu='$statu' , homework='$work' , lessontest='$test' , score ='$score'  WHERE student_id='$id'");

 if (mysql_affected_rows()) {
  echo "{\"statu\":\"success\"}";
 }else {
  echo "{\"statu\":\"error\"}";
 }

}
mysql_close($con);
?>
