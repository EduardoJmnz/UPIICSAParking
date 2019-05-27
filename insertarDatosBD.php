<?php
$hostname = 'localhost';
$database = 'id9728474_upiiparking';
$username = 'id9728474_emmanuel';
$password = 'ironman741';

$json = array();
if(isset($_REQUEST["boleta"]) && isset($_REQUEST["folio"]) && isset[$_REQUEST["contraseña"]){
    $boleta =$_REQUEST['boleta'];
    $folio = $_REQUEST['folio'];
    $contraseña = $_REQUEST['contraseña'];
    
    $conexion = mysqli_connect($hostname, $username, $password, $database);
$consulta = "INSERT INTO ganadores (contraseña) VALUES ('{$contraseña}')WHERE boleta = '{boleta}'folio FROM ganadores WHERE boleta= '{$boleta}' AND folio = '{$folio}'";
    $resultado = mysqli_query($conexion, $consulta) or die (mysqli_error());
   
?>