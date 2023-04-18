$(function (){
    $('#update_canteen_button').click(function (){
        $('.form .field').removeClass('disabled')
        this.style.display = 'none'
        let v = $('#update_canteen_confirm_button');
        for (let i = 0; i < v.length; i++) {
            v[i].style.display = 'block'
        }
    })
})