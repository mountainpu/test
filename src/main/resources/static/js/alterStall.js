$(function (){
    let option = [];
    $.ajax({
        url:'/findAllCanteen',
        type:'GET',
        contentType:false,
        processData:false,
        dataType:"json",
        async:false,
        success:function (response){
            console.log(response)
            for (let i = 0; i < response.length; i++) {
                option.push(response[i])
            }
        }
    })
    let select = $('#select_canteen');
    for (let i = 0; i < option.length; i++) {
        let val = option[i].canteen_id;
        let con = option[i].canteen_name;
        select.append(
            "<option value='"+val+"'>"+con+"</option>"
        )
    }
    $('#alter_button').click(function (){
        $('.post_form .field').removeClass('disabled')
        this.style.display = 'none'
        let v = $('#confirm_button');
        for (let i = 0; i < v.length; i++) {
            v[i].style.display = 'block'
        }
    })
})