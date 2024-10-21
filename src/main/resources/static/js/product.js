function openForm() {
    $.ajax({
        type: "get",
        url: "/product/form",
        contentType: "html",
        success: function (productForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Product Form");
            $('.modal-body').html(productForm);
        }
    });
}

function editForm(id) {
    $.ajax({
        type: "get",
        url: `/product/edit/${id}`,
        contentType: `html`,
        success: function (productForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Product Form");
            $('.modal-body').html(productForm);
        }
    });
}

function deleteForm(id) {
    $.ajax({
        type: "get",
        url: `/product/deleteForm/${id}`,
        contentType: `html`,
        success: function (productForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Product Form");
            $('.modal-body').html(productForm);
        }
    });
}

function deleteProduct(id) {
    $.ajax({
        type: "get",
        url: `/product/soft-delete/${id}`,
        contentType: `html`,
        success: function (response) {
            location.reload();
        }
    });
}