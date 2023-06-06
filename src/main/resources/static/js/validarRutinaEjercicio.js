function validarRutinaEjercicio() {
  const fechaSeleccionada = document.getElementById('inputDate').value;
  const rutinaSeleccionada = document.getElementById('selectedOption').value;
  const ejerciciosIdsInput = document.getElementById('ejerciciosIds').value;

  // Validar si se ha seleccionado una rutina en una fecha específica
  if (!fechaSeleccionada || !rutinaSeleccionada) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'Por favor, selecciona una fecha y una rutina válida.',
    });
    return false;
  }

  // Validar si se han asignado ejercicios repetidos para la misma fecha y rutina
  const ejerciciosIdsAnteriores = ejerciciosIdsInput.split(',');
  if (ejerciciosIdsAnteriores.includes(rutinaSeleccionada)) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'No puedes seleccionar el mismo ejercicio más de una vez para la misma fecha y rutina.',
    });
    return false;
  }

  // Validar si se ha agregado al menos 1 ejercicio
  if (ejerciciosIdsAnteriores.length === 0) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'Debes agregar al menos 1 ejercicio.',
    });
    return false;
  }

  return true;
}