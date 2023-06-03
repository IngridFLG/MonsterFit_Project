
    /**
     * Funcion para hacer las validaciones en el formulario de agregar ejercicio
     */
    function validarFormulario () {
        let series = document.getElementById('series').value;
        let repeticiones = document.getElementById('repeticiones').value;
        let peso = document.getElementById('peso').value;
        let tiempo = document.getElementById('tiempo').value;
        let url = document.getElementById('url').value;
        let validarUrl = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/;
        
        if (validarUrl.test(url) === false) {
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: 'La URL no es válida.'
            });
            return false;
          }

        if (tiempo === '' && repeticiones === '' && peso === '' && series === '') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Debes llenar al menos un campo de peso, tiempo, series o repeticiones.'
            });
            return false;
        }
    
        if (tiempo !== '' && (series !== '' || repeticiones !== '' || peso !== '')) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Si colocas tiempo, no puedes colocar series, repeticiones o peso.'
            });
            return false;
        }
    
        if ((isNaN(series) || isNaN(repeticiones)) && (series !== '' || repeticiones !== '')) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Si llenas el campo de series, también debes llenar el campo de repeticiones y viceversa.'
            });
            return false;
        }
    
        if (series > 0 && repeticiones === '') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Por favor, ingresa un valor para repeticiones.'
            });
            return false;
        }
    
        if (repeticiones > 0 && series === '') {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Por favor, ingresa un valor para series.'
            });
            return false;
        }
    
        if (peso !== '' && (series === '' || repeticiones === '')) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Si colocas peso, también debes colocar series y repeticiones.'
            });
            return false;
        }
    
        return true;
    }
    