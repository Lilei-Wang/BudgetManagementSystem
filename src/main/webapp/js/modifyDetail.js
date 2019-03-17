function equip(btn) {
    var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
    var num=btn.parentElement.firstElementChild.value;
    $.ajax({
        url:"${pageContext.request.contextPath}/Budget/Modify/Equip",
        type:"post",
        data:{
            mode:0,
            id:id,
            nums:num
        },
        success:function () {
            alert("success")
        }
    })
}