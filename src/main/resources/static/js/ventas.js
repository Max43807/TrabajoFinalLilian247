// In your Javascript (external .js resource or <script> tag)
$(document).ready(function () {
    $('.select-productos').select2();

    if ($("#success").text() !== "") {
        Swal.fire(
                'Ventas',
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
});


$("#select_productos").on("change", () => {

    let linea = $("#ventaItems").html();
    let id = $("#select_productos").val();
    let producto = $("#select_productos option:selected").text();
    let descripcion = producto.split('-')[1];
    let precio = producto.split('-')[2];
    //console.log(producto);

    if (lineasUtil.esRepetido(id)) {
        console.log("Ya hay ese producto");
        lineasUtil.incrementarCantidad(id);
        limpiar();
        return false;
    }

    linea = linea.replace(/{ID}/g, id);
    linea = linea.replace(/{DESCRIPCION}/g, descripcion);
    linea = linea.replace(/{PRECIO}/g, precio);
    linea = linea.replace(/{CANTIDAD}/g, 1);

    $("#tabla tbody").append(linea);

    lineasUtil.calcularSubtotal(id, precio, 1);

    limpiar();
});

const limpiar = () => {
    $("#select_productos").val("");
    $("#select_productos").trigger("select");
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


