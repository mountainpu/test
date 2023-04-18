$(function (){
    $('#upload_dish_confirm_button').click(function (){
        $('.ui.form').submit()
    })
    $('#update_dish_confirm_button').click(function (){
        let a = $('#flavour .addition')
        for (let i = 0; i < a.length; i++) {
            let v = a[i].getAttribute('value')
            a[i].setAttribute('value', "%%a%%"+v)
        }
        $('.ui.form').submit()
    })
    $('#category').change(function (){
        if (this.options[this.selectedIndex].getAttribute('class')==='addition'){
            this.setAttribute('name', 'category_name')
        }
        else{
            this.setAttribute('name', 'category_id')
        }
    })
    $('#update_dish_button').click(function (){
        $('.form .field').removeClass('disabled')
        this.style.display = 'none'
        let v = $('#update_dish_confirm_button');
        for (let i = 0; i < v.length; i++) {
            v[i].style.display = 'block'
        }
    })
})