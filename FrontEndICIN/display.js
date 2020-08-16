
$(function() {
    $('input[name="transactionType"]').on('click', function() {
        if ($(this).val() == 'self') {

            $('#form-1').show();
            $('#form-2').hide();
            $('#form-3').hide();
            

        }
        if  ($(this).val() == 'same') {
            $('#form-1').hide();
            $('#form-2').show();
            $('#form-3').hide();
        }

        if($(this).val() == 'other'){
            $('#form-1').hide();
            $('#form-2').hide();
            $('#form-3').show();
        }


    });
});

     