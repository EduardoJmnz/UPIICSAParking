<?php
$hostname = '185.201.11.189';
$database = 'u700687137_upark';
$username = 'u700687137_park';
$password = '012345';

$json = array();
if(isset($_GET["boleta"])){
   $boleta =$_GET['boleta'];
    $folio = $_GET['folio'];
    $conexion = mysqli_connect($hostname, $username, $password, $database);
    $consulta = "SELECT nombre, boleta, placas, modelo, color FROM usuarios WHERE boleta= '{$boleta}'";
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
			$results["nombre"]='';
            $results["placas"]='';
            $results["modelo"]='';
            $results["color"]='';
			$json['datos'][]=$results;
			echo json_encode($json);
		}
    
}else{
    $results["boleta"]='';
    $results["nombre"]='';
    $results["placas"]='';
    $results["modelo"]='';
    $results["color"]='';
    $json['datos'][]=$results;
    echo json_encode($json);
}

?>