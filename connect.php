<?php
$hostname = 'localhost';
$database ='id9728474_upiiparking';
$username='id9728474_emmanuel';
$password='ironman741';

$conexion = new mysqli ($hostname, $username, $password, $database);
if($conexion ->connect_errno){
    echo "No se pudo conectar ala BD";
}

?>