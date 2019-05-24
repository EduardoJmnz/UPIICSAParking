<?php
$hostname = '185.201.11.189';
$database = 'u700687137_upark';
$username = 'u700687137_park';
$table = 'ganadores';

$json = array();
if(isset($_GET["boleta"]) && isset($_GET["folio"])){
   $boleta =$_GET['boleta'];
    $folio = $_GET['folio'];
    $conexion = mysqli_connect($hostname, $username, $password, $database);
    $consulta = "SELECT boleta, folio FROM ganadores WHERE boleta= '{$boleta}' AND folio = '{$folio}'";
    $resultado = mysqli_query($conexion, $consulta)
    
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