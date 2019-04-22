<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/amazeui.min.css" />
</head>
<body>
<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">添加商品</strong><small></small></div>
    </div>
    <hr>

    <div class="edit_content">
		<form action="${pageContext.request.contextPath }/GoodsAddServlet" enctype="multipart/form-data" id="add_form" method="post" style="background: none; width: 700px">
	        <div class="item1">
	            <div>
	                <span>商品名称：</span>
	                <input type="text" class="am-form-field" name="name" >&nbsp;&nbsp;
	            </div>
	            <div>
	                <span>商品价格：</span>
	                <input type="text" class="am-form-field" name="price" >
	            </div>
	
	        </div>
	
	        <div class="item1">
	                <span>所属分类：</span>
	                 <select id="category_select" name="cid"> 
	                   <c:forEach items="${allCategory }" var="category">
	                   		<option value="${category.cid }">${category.cname }</option>
	                   </c:forEach>
	 				</select>
	                 &nbsp;
	        </div> 
	        
	        <div class="item1">
	        	<span>是否热门：</span>
	        	<select id="category_select" name="is_hot">
	        		<option value="1">否</option>
	        		<option value="0">是</option>
	        	</select>
	        </div>
	
	        <div class="item1 update_pic">
	                <span>商品图片：</span>
	                <img src="" id="imageview" class="item_img" style="display: none;">
	                <label for="fileupload" id="label_file">上传文件</label>
	                <input type="file" name="upload" id="fileupload" />
	        </div>
	        
	        <div class="item1 item_desc">
	            <span>商品描述：</span>
	            <textarea  id="desc" name="gdesc" rows="4" cols="50"></textarea>
	        </div>
	        <button class="am-btn am-btn-default" type="button" id="add">添加</button>
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <button class="am-btn am-btn-default" type="button" id="reset">重置</button>
        </form>
   </div>
</div>

<script src="${pageContext.request.contextPath }/admin/js/jquery.min.js"></script>

<script>
    $("#add").click(function () {
        /* $(window).attr('location','${pageContext.request.contextPath }/admin/main.jsp'); */
        //让表单提交GoodsAddServlet
        //获取表单
        $("#add_form").submit();
    });
    
    $("#reset").click(function () {
       $(window).attr('location','${pageContext.request.contextPath }/admin/edit.jsp');
    });
    
    
    
    $("#fileupload").change(function(){
    	var $file = $(this);
    	var objUrl = $file[0].files[0];
    	var windowURL = window.URL || window.webkitURL;
    	var dataURL;
    	dataURL = windowURL.createObjectURL(objUrl);
    	$("#imageview").attr("src",dataURL);
    	console.log($('#imageview').attr('style'));
    	if($('#imageview').attr('style')==='display: none;'){
    		$('#imageview').attr('style','inline');
    		$('#imageview').width('30px');
    		$('#imageview').height('20px');
    		$('.update_pic').attr('style','margin-bottom: 10px');
    	}
    	
    });

</script>
</body>
</html>