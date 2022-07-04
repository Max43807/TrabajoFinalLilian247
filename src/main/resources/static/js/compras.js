// In your Javascript (external .js resource or <script> tag)
$(document).ready(function () {
    $('.select-articulos').select2();

    if ($("#success").text() !== "") {
        Swal.fire(
                '! Tu Compra fue con Exito !',
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

    if ($("#danger").text() !== "") {
        Swal.fire({

            title: 'Compra Eliminada',
            confirmButtonColor: '#d33',
            confirmButtonText: 'Aceptar'
            
        })
    }

});



$("#select_articulos").on("change", () => {

    let linea = $("#compraItems").html();
    let id = $("#select_articulos").val();
    let articulo = $("#select_articulos option:selected").text();
    let descripcion = articulo.split('-')[1];
    let precio = articulo.split('-')[2];
//    console.log(articulo);


    if (lineasUtil.esRepetido(id)) {
        console.log("ya existe ese articulo");
        lineasUtil.incrementarCantidad(id);
        
        limpiar();
        return false;
    }

    linea = linea.replace(/{ID}/g, id);
    linea = linea.replace(/{DESCRIPCION}/g, descripcion);
    linea = linea.replace(/{PRECIO}/g, precio);
    linea = linea.replace(/{CANTIDAD}/g, 1);

    $("#nueva tbody").append(linea);


    lineasUtil.calcularSubtotal(id, precio, 1);
  
   
    
    limpiar();
});

const limpiar = () => {
    $("#select_articulos").val("");
    $("#select_articulos").trigger("select");   
}

const lineasUtil = {
    esRepetido: function (id) {
        let resultado = false;
        $('input[name="item_id[]"]').each(function () {
            if (parseInt(id) == parseInt($(this).val())) {
                resultado = true;
            }
        });
        return resultado;
    },

    incrementarCantidad: function (id) {
        let cantidad = $("#cantidad_" + id).val() ? parseInt($("#cantidad_" + id).val()) : 0;
        $("#cantidad_" + id).val(++cantidad);
        this.calcularSubtotal(id, $(`#precio_actual_${id}`).val(), cantidad);
    },

    calcularSubtotal: function (id, precio, cantidad) {
        $(`#subtotal_${id}`).html((parseFloat(precio) * parseInt(cantidad)).toFixed(2));
        this.calcularTotal();     
    },
    
    calcularTotal: function () {
        let total = 0;
        $(`span[id^="subtotal_"]`).each(function () {
            total += parseFloat($(this).html());
        });
        $("#total").html("$" + parseFloat(total).toFixed(2));
     
    }


  
}

$(document).ready(function () {
    $('.select-proveedores').select2();

});
