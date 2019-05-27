<?php
$hostname = 'localhost';
$database = 'id9728474_upiiparking';
$username = 'id9728474_emmanuel';
$password = 'ironman741';

$json = array();
if(isset($_GET["boleta"]) && isset($_GET["folio"])){
   $boleta =$_GET['boleta'];
    $folio = $_GET['folio'];
    $conexion = mysqli_connect($hostname, $username, $password, $database);
    $consulta = "SELECT boleta, folio FROM ganadores WHERE boleta= '{$boleta}' AND folio = '{$folio}'";
    $resultado = mysqli_query($conexion, $consulta);
    
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
			$json['datos'][]=$results;
			echo json_encode($json);
		}
    
}else{
    $results["boleta"]='';
    $results["folio"]='';
    $json['datos'][]=$results;
    echo json_encode($json);
}

?>