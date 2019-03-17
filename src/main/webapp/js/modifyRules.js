function equip(btn,curd) {
    var id=btn.parentElement.parentElement.firstElementChild.innerHTML;
    var num=btn.parentElement.firstElementChild.value;
    var name=btn.parentElement.firstElementChild.nextElementSibling.value;
    var price=btn.parentElement.firstElementChild.nextElementSibling.nextElementSibling.value;
    $.ajax({
        url:"${pageContext.request.contextPath}/Budget/Modify/Equip",
        type:"post",
        data:{
            mode:1,
            id:id,
            name:name,
            price:price,
            curd:curd
        },
        success:function () {
            alert("success")
        }
    })
}