$(document).ready(function() {
const tabla =	$('#tablaProveedores').DataTable({
		responsive : true,
		lengthMenu : [ 3, 5, 7, 10 ],
		"language" : {
			"lengthMenu" : "Mostrar _MENU_ registros por página",
			"search" : "Buscar proveedor",
			"zeroRecords" : "No hay registros",
			"info" : "Mostrando página _PAGE_ de _PAGES_",
			"infoEmpty" : "No hay registros",
			"infoFiltered" : "(Encontrados _MAX_ de registros)",
			"paginate" : {
				"first" : "Primero",
				"last" : "Último",
				"next" : "Siguiente",
				"previous" : "Anterior"
			}
		}
	});

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


setInterval('cerrar()', 3000);
function cerrar() {
	$(".alert").delay(300).slideUp(50, function() {
		$(this).hide();
	});

}

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
                        url: "/proveedores/borrar/" + id,
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
                                    location.href = "/proveedores/listado";
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