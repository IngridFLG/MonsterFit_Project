function validarFormularioHistorial() {
    let series = document.getElementById('series').value;
    let repeticiones = document.getElementById('repeticiones').value;
    let peso = document.getElementById('peso').value;

    if (peso !== '') {
        return validarFormularioHistorialConPeso(peso, series, repeticiones);
    } else {
        return validarFormularioHistorialSinPeso(series, repeticiones);
    }
}


function validarFormularioHistorialConPeso(peso, series, repeticiones) {


    if (parseInt(peso) === 0 || parseInt(series) === 0 || parseFloat(repeticiones) === 0) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor coloca campos mayores a cero.'
        });
        return false;
    }

    if (series === '' || repeticiones === '') {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Si colocas peso, también debes colocar series y repeticiones.'
        });
        return false;
    }


    return true;
}


function validarFormularioHistorialSinPeso(series, repeticiones) {

    if ((isNaN(series) || isNaN(repeticiones)) && (series !== '' || repeticiones !== '')) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Si llenas el campo de series, también debes llenar el campo de repeticiones y viceversa.'
        });
        return false;
    }

    if (series > 0 && (repeticiones === '' || repeticiones === 0)) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor, ingresa un valor para repeticiones.'
        });
        return false;
    }

    if (repeticiones > 0 && (series === '' || series === 0)) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor, ingresa un valor para series.'
        });
        return false;
    }

    return true;
}
