function validarFormularioCliente() {
    let edad = document.getElementById('edad').value;
    let peso = document.getElementById('peso').value;
    let sexo = document.getElementById('sexo').value;
    let altura = document.getElementById('altura').value;

    if(parseInt(edad) <= 0 || parseInt(edad) > 15){
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Debes tener al menos 15 años para ser miembro.'
        });
        return false;
    }

    if(parseInt(peso) <= 0){
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor coloca campos mayores a cero.'
        });
        return false;
    }

    if (!/^([MF])$/.test(sexo)) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor, ingresa una letra válida para el sexo (M o F).'
        });
        return false;
    }

    if (parseInt(altura) <= 100) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor, ingresa una altura mayor a 100 cm.'
        });
        return false;
    }

    return true;
}