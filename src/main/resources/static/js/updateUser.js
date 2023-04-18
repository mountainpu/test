$(function (){
    $('#add_address_button').click(function (){
        let len = $('.address').length + 1;
        $('.form-fields').append(
            "<div class='address field required'>"+
                "<span>"+
                "<label for=address" + len + ">" + "地址信息" + len +"</label>"+
                "<i class='ui red close link icon' id='icon'" + len + "></i>"+
                "</span>"+
                "<input type=text id=address"+len+" name=address_name placeholder=请输入新的地址 maxlength='64' required>"+
            "</div>"
        )
    })
    $('#update_user_button').click(function (){
        $('.form-fields .field').removeClass('disabled')
        this.style.display = 'none'
        let v = $('#update_user_confirm_button');
        for (let i = 0; i < v.length; i++) {
            v[i].style.display = 'block'
        }
        let t = $('#add_address_button');
        for (let i = 0; i < t.length; i++) {
            t[i].style.display = 'block'
        }
    })
    $('.close.icon').click(function (){
        let idx = this.getAttribute('id').substring(4,5)
        console.log(idx)
        $('#'+idx).remove()
    })
})