<?php
include 'connect.php';
$boleta = $_POST['boleta'];
$folio = $_POST['folio'];
$validador = $_POST['validador'];

$consulta = "INSERT INTO ganadores (Dentro_Fuera) VALUES ('.$validador.')FROM ganadores WHERE boleta= '.$boleta.' AND folio = '.$folio.'";
    $resultado = mysqli_query($conexion, $consulta) or die (mysqli_error());
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
?>
