<?php

$con = mysql_connect("127.0.0.1","root","123456");

if ($con) {
 mysql_select_db("llw", $con);
 //echo $_GET['id'];
 $id = $_GET['id'];
 $pw = $_GET['pw'];

 $result = mysql_query("SELECT * FROM login where loginid = $id  and loginpw = $pw");


   $row = mysql_fetch_array($result);
   if (!$row) {
     echo "0";
   }else {
    $sta = $row['loginstatu'];
    echo "$sta";;
   }
}
mysql_close($con);
?>
