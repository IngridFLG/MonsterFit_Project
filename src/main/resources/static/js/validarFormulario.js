

    /**
     * Funcion para hacer las validaciones en el formulario de agregar ejercicio
     */
    function validarFormulario()  {
        let series = document.getElementById('series').value;
        let repeticiones = document.getElementById('repeticiones').value;
        let peso = document.getElementById('peso').value;
        let tiempo = document.getElementById('tiempo').value;

        if (tiempo === '' && repeticiones === '' && peso === '' && series === '') {
            alert('Al menos debes llenar un campo de peso, tiempo, series o repeticiones.');
            return false;
        }

        if (tiempo !== '' && (series !== '' || repeticiones !== '' || peso !== '')) {
            alert('Si colocas tiempo no puedes colocar series, repeticiones o peso.');
            return false;
        }

        if ((isNaN(series) && !isNaN(repeticiones)) || (!isNaN(series) && isNaN(repeticiones))) {
            alert('Si llenas el campo de Series, también debes llenar el campo de Repeticiones y viceversa.');
            return false;
        }

        if (series > 0 && repeticiones === 0) {
            alert('Por favor, ingresa un valor mayor a cero para Repeticiones.');
            return false;
        }

        if (repeticiones > 0 && series === 0) {
            alert('Por favor, ingresa un valor mayor a cero para Series.');
            return false;
        }

        if (peso !== '' && (series === '' || repeticiones === '')) {
            alert('Si colocas peso tambien debes colocar series y repeticiones.');
            return false;
        }

        return true;

    }
