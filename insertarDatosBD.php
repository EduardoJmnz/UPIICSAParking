<?php
$hostname = 'localhost';
$database = 'id9728474_upiicsa';
$username = 'id9728474_emmanuell';
$password = 'ironman741';
$boleta = $_GET['boleta'];
$folio = $_GET['folio'];
$validador = $_GET['validador'];

$json = array();
if(isset($_GET["boleta"]) && isset($_GET["folio"]) && isset($_GET["validador"])){
   $boleta =$_GET['boleta'];
    $folio = $_GET['folio'];
$validador = $_GET['validador'];
$consulta = "INSERT INTO ganadores (Dentro_Fuera) VALUES ('{$validador}')FROM ganadores WHERE boleta= '{$boleta}' AND folio = '{$folio}'";
if($consulta){
        if($reg = mysqli_fetch_array($resultado)){
                    $json['datos'][]=$reg;
        }
        mysqli_close($conexion);
        echo json_encode($json);
        
    }


    else{
			$results["boleta"]='';
			$results["folio"]='';
			$results["validador"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}


}else{
    $results["boleta"]='';
    $results["folio"]='';
$resuts["validador"]='';
    $json['datos'][]=$results;
    echo json_encode($json);
    
?>
