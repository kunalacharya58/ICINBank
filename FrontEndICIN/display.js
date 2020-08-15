
$(function() {
    $('input[name="transactionType"]').on('click', function() {
        if ($(this).val() == 'self') {
            $('#form-2').hide();
            $('#form-1').show();

        }
        else {
            $('#form-1').hide();
            $('#form-2').show();
        }
    });
});

     