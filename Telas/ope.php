<?php
 

define('DB_SERVER', '127.0.0.1');
   define('DB_USERNAME', 'root');
   define('DB_PASSWORD', '');
   define('DB_DATABASE', 'NoShow');
   $db = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

session_start();


 if($_SERVER["REQUEST_METHOD"] == "POST") {
      $myusername = mysqli_real_escape_string($db,$_POST['usuario']);
      $mypassword = mysqli_real_escape_string($db,$_POST['senha']); 
      
      $sql = "SELECT * FROM profissional WHERE Usuario = '$myusername' and Senha = '$mypassword'";
      $result = mysqli_query($db,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
        $_SESSION['usuario'] = $myusername;
        header("location: menu.php");
      }else {
        header("location: index.html");
      }
   }




?>
