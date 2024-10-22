function openForm() {
    $.ajax({
        type: "get",
        url: "/variant/form",
        contentType: "html",
        success: function (variantForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Variant Form");
            $('.modal-body').html(variantForm);
        }
    });
}

function updateProduct(categoryId){
    $.ajax({
        type: "get",
        url: `/variant/product-category/${categoryId}`,
        success: function (response) {
            const productSelect = document.getElementById("productId");
            productSelect.innerHTML = '<option value="0" selected="true" disabled="true">Select Product</option>';

            response.forEach(product => {
                const newOption = document.createElement("option");
                newOption.value = product.id;
                newOption.text = product.name;
                productSelect.appendChild(newOption);
            })
        },
        error: function (response) {
            console.log(response);
        }
    })
}

function editForm(id) {
    $.ajax({
        type: "get",
        url: `/variant/edit/${id}`,
        contentType: `html`,
        success: function (variantForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Variant Form");
            $('.modal-body').html(variantForm);

            const categoryId = $('#categoryId').val();
            const selectedProductId = $('#productId').val();

            // const variantData = $('#variantForm').data('variant');
            // const selectedCategoryId = variantData.product.categoryId;
            // const selectedProductId = variantData.productId;

            $('#categoryId').val(categoryId);
            updateProduct(categoryId);

            // $('#categoryId').val(selectedCategoryId);
            // updateProduct(selectedCategoryId);
            setTimeout(() => {
                $('#productId').val(selectedProductId);
            }, 500);
        }
    });
}

function deleteForm(id) {
    $.ajax({
        type: "get",
        url: `/variant/deleteForm/${id}`,
        contentType: `html`,
        success: function (variantForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Variant Form");
            $('.modal-body').html(variantForm);
        }
    });
}

function deleteVariant(id) {
    $.ajax({
        type: "get",
        url: `/variant/soft-delete/${id}`,
        contentType: `html`,
        success: function (response) {
            location.reload();
        }
    });
}