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

    $('.money').mask("#.##0,00", {reverse: true});

});

