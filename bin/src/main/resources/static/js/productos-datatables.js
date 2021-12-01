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

$("#btnNuevo").on("click", () => {
    $("#tituloModal").html("Nuevo Producto");
    $(".form-control").val("");
    limpiarErrores();
    $("#myModal").modal("show");
});

$("#btnGuardar").on("click", () => {

    //Crear un objeto articulo:
    let articulo = {};

    articulo.codBarras = $("#codBar").val();
    articulo.descripcion = $("#descripcion").val();
    articulo.precio = $("#precio").val();
//    $("#stock").val() === ""
//        ? articulo.stock = 0 
//        : articulo.stock = $("#stock").val();
    if ($("#stock").val() === "")
        $("#stock").val("0");
    articulo.stock = $("#stock").val();

    $.ajax({
        method: "POST",
        url: "/productos/guardar",
        data: articulo,
        beforeSend: function () {
            limpiarErrores();
        },
        success: function () {
            $("#myModal").modal("hide");
            //alert("Producto guardado!");
            Swal.fire({
                    title: 'Éxito!',
                    text: 'Producto guardado.',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
             }).then((result) => {
                 if(result.isConfirmed) {
                     location.reload();
                 }
             });
        },
        complete: function () {

        },
        statusCode: {
            422: function (xhr) {
                let errors = $.parseJSON(xhr.responseText);
                console.log("Errores: " + xhr.status);
                $.each(errors, function (key, val) {
                    $("#" + key).addClass("is-invalid");
                    $("#error-" + key).addClass("invalid-feedback")
                            .append("<span class='error-span'>" + val + "</span>");
                });
            }
        }
    });

});

const limpiarErrores = () => {
    $(".is-invalid").removeClass("is-invalid");
    $("span").closest(".error-span").remove();
}


