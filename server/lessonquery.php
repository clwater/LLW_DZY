<?php
header("Content-type: text/html; charset=utf-8");
$con = mysql_connect("127.0.0.1","root","123456");
mysql_query("set names utf8");

if ($con) {
 mysql_select_db("llw", $con);

 mysql_query("SET NAMES UTF8");
 mysql_query("set character_set_client=utf8");
 mysql_query("set character_set_results=utf8");


  $result = mysql_query("SELECT * FROM lesson ");
  $return = "{\"statu\":\"ok\",\"student\": [";
  $num = mysql_num_rows($result);
 if ($num==0) {
  echo "{\"statu\":\"error\"}";
 }else{

   $index = 1;
   while($row = mysql_fetch_array($result))
   {
    $return = $return.""."{\"name\":\"";
     $return = $return."".$row['student_name'];
     $return = $return.""."\",\"id\":\"";
     $return = $return."".$row['student_id'];

     $return = $return.""."\",\"attendance\":\"";
     $return = $return."".$row['attendance'];

     $return = $return.""."\",\"lessonstatu\":\"";
     $return = $return."".$row['lessonstatu'];

     $return = $return.""."\",\"homework\":\"";
     $return = $return."".$row['homework'];

     $return = $return.""."\",\"lessontest\":\"";
     $return = $return."".$row['lessontest'];

     $return = $return.""."\",\"score\":\"";
     $return = $return."".$row['score'];
     
     $return = $return.""."\"}";
     if ($index != $num) {
      $return = $return."".",";
     }
     $index++;
   }
   $return = $return.""."]}";
   echo "$return";
}

}
mysql_close($con);
?>
