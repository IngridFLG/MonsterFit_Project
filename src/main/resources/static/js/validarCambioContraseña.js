// Obtén una referencia al formulario
let form = document.querySelector('#myForm');
  
// Agrega un evento de envío al formulario
form.addEventListener('submit', function(event) {
  event.preventDefault(); // Evita la acción de envío predeterminada del formulario

  // Muestra un modal SweetAlert para confirmar la actualización
  Swal.fire({
    title: 'Confirmar actualización',
    text: '¿Estás seguro de que deseas actualizar la contraseña del usuario?',
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: 'Actualizar',
    cancelButtonText: 'Cancelar',
  }).then((result) => {
    if (result.isConfirmed) {
      // Si se confirma la actualización, envía el formulario
      form.submit();
    }
  });
});