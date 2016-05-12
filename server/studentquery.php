<?php
header("Content-type: text/html; charset=utf-8");
$con = mysql_connect("127.0.0.1","root","123456");
mysql_query("set names utf8");

if ($con) {
 mysql_select_db("llw", $con);


 $result = mysql_query("SELECT * FROM student ");


   $row = mysql_fetch_array($result);
   if (!$row) {
     echo "{\"statu\":\"error\"}";
   }else {
    $name = $row['student_name'];
    $id = $row['student_id'];
    $class = $row['student_class'];
    echo "$name<br>$id<br>$class" ;
   }
}
mysql_close($con);
?>
