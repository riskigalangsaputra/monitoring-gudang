initSelectKategori();
initSelectAlatkerja();


function initSelectKategori() {
    $(".select2-kategori").select2({
        placeholder: '-- Pilih Kategori --',
        ajax: {
            url: function (params) {
                return baseUrl + "api/selection/kategori";
            },
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    sort: "nama,asc",
                    value: params.term,
                    page: params.page || 1
                };
            }
        }
    });
}

function initSelectAlatkerja() {
    $(".select2-alatkerja").select2({
        placeholder: '-- Pilih Barang --',
        ajax: {
            url: function (params) {
                return baseUrl + "api/selection/alatkerja";
            },
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    type: $(".select2-kategori").val(),
                    sort: "code,asc",
                    value: params.term,
                    page: params.page || 1
                };
            }
        }
    });
}

$(".select2-kategori").on("change", function () {
    $(".select2-alatkerja").val(null).trigger('change');
});