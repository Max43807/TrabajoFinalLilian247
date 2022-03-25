$(document).ready(function () {

    const tabla = $('#tablaProductos').DataTable({
        order: [[0, "desc"]],
        lengthMenu: [5, 10, 15, 20, 30, 50, 100],
        language: {
            "search": "Buscar: ",
            "lengthMenu": "Mostrar _MENU_ registros por página",
            "info": "Mostrando de _START_ a _END_ de _TOTAL_ de registros",
            "infoFiltered": "(Filtrado de _MAX_ registros)",
            "paginate": {
                "previous": "Anterior",
                "next": "Siguiente"
            }
        }
    });
	
	if ($("#success").text() !== "") {
        Swal.fire(
                '! Guardado correctamente !',
                $("#success").text(),
                'success'
                )
    }

	
	if ($("#error").text() !== "") {
        Swal.fire(
                'Error',
                $("#error").text(),
                'error'
                )

    }

	
    $('.money').mask("#.##0,00", {reverse: true});

});

function borrar(id) {
//	console.log(id);
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    });
    swal.fire({
        title: '<span class="text-danger">¿Quieres eliminar este articulo?',
//      text: "No podrás revertir esto",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#218838',
        confirmButtonText: 'Aceptar',
        cancelButtonColor: '#dc3545',
        reverseButtons: true,
        footer: '<span class="text-danger">No podrás revertir esto',
        
        padding: '1rem',
        width: '31%',
        backdrop: true,
        allowOutsideClick: false,
        allowEscapeKey: false,
        allowEnterKey: false,
        stopKeydownPropagation: false,
        showConfirmButton: true
    })
            .then((result) => {

                if (result.isConfirmed) {
                    $.ajax({
                        url: "/articulos/borrar/" + id,
                        success: function (res) {
                            console.log(res);
                        },
                    });
                    swal.fire({
                        title: '<span class="text-success">Articulo Eliminado',
//                      text: "No podrás revertir esto",
                        icon: 'success',
//                        showCancelButton: true,
                        confirmButtonColor: '#218838',
                        confirmButtonText: 'Aceptar',
//                        cancelButtonColor: '#dc3545',
                        reverseButtons: true,
                        footer: '<span class="text-success">Ya no éxiste este articulo',
                        padding: '1rem',
                        width: '31%',
                        backdrop: true,
                        allowOutsideClick: false,
                        allowEscapeKey: false,
                        allowEnterKey: false,
                        stopKeydownPropagation: false,
                        showConfirmButton: true
                    })
                            .then((result) => {
                                if (result.isConfirmed) {
                                    location.href = "/articulos/listado";
                                }
                            });
                } else if (
                        /* Read more about handling dismissals below */
                        result.dismiss === Swal.DismissReason.cancel
                        ) {
                    swal.fire({
                        title: '<span class="text-danger">Cancelado',
//                      text: "No podrás revertir esto",
                        icon: 'error',
//                        showCancelButton: true,
                        confirmButtonColor: '#dc3545',
                        confirmButtonText: 'Cancelar',
//                        cancelButtonColor: '#dc3545',
                        reverseButtons: true,
                        footer: '<span class="text-danger">Este articulo fue anulado',
                        padding: '1rem',
                        width: '31%',
                        backdrop: true,
                        allowOutsideClick: false,
                        allowEscapeKey: false,
                        allowEnterKey: false,
                        stopKeydownPropagation: false,
//                        showConfirmButton: true
                    });
                }
            });
}
