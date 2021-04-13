<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>board : write</title>
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css" />
	<script type="text/javascript">
		function check(){
		    if(!bbs.subject.value){
		        alert("제목을 입력하세요");
		        bbs.subject.focus();
		        return false;
		    }
		    if(!bbs.writer.value){
		        
		        alert("이름을 입력하세요");
		        bbs.writer.focus();
		        return false;
		    }
		   /*  if(!bbs.content.value){            
		        alert("글 내용을 입력하세요");
		        bbs.content.focus();
		        return false;
		    } */
		    if(!bbs.pwd.value){            
		        alert("비밀번호를 입력하세요");
		        bbs.pwd.focus();
		        return false;
		    }
		 
		    document.bbs.submit();
		 
		}
	</script>
	<!-- 첨부파일 <file> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- summbernote -->
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#filename').on("change", fileChange);
			$('#summernote').summernote({
				placeholder: '내용을 입력하세요',
				tabsize: 2,
				height: 300,
				minHeight: 300, 
				maxHeight: 300, 
			});			
		})
		
		function fileChange(e){
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);
			
			filesArr.forEach(function(f){
				var reader = new FileReader();
				reader.onload = function(e){
					$('#preview').attr("src", e.target.result);
				}
				reader.readAsDataURL(f);
			})
		}
		
	</script>
<!-- 첨부파일 <file> -->
</head>
<body>
	<c:import url="/WEB-INF/include/header.jsp" />

    <div id="pageContainer">
        <div style="padding-top: 25px; text-align: center">
            <!-- form 시작 ---------->
            <form name="bbs" action="writeok.board" method="POST" enctype="multipart/form-data">
                <table width="95%" border="2" align="center">
                    <tr>
                        <td width="20%" align="center">제목</td>
                        <td width="80%" align="left"><input type="text" name="subject" size="40"></td>
                    </tr>
                    <tr>
                        <td width="20%" align="center">글쓴이</td>
                        <td width="80%" align="left"><input type="text" name="writer" size="40"></td>
                    </tr>
                    <tr>
                        <td width="20%" align="center">이메일</td>
                        <td width="80%" align="left"><input type="text" name="email" size="40"></td>
                    </tr>
                    <tr>
                        <td width="20%" align="center">홈페이지</td>
                        <td width="80%" align="left"><input type="text" name="homepage" size="40" value="http://"></td>
                    </tr>
                    <tr>
                        <td width="20%" align="center">글내용</td>
                        <td width="80%" align="left">
                        	<textarea rows="10" cols="60" name="content" class="ckeditor" id="summernote"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" align="center">비밀번호</td>
                        <td width="80%" align="left"><input type="password" name="pwd" size="20"></td>
                    </tr>
					<!-- 첨부파일 <file> 파일 id값 설정 -->
                    <tr>
                    	<td width="20%" align="center">첨부파일</td>
                        <td width="80%" align="left"><input type="file" id="filename" name="filename"></td>
                    </tr>
                    <tr>
                    	<td width="20%" align="center">미리보기</td>
                        <td width="80%" align="left"><img id="preview" src="" width="300" alt=""></td>
                    </tr>
                    <!-- 첨부파일 <file> 미리보기 tr 추가-->
                    <tr>
                        <td colspan="2" align="center">
                            <input type="button" value="글쓰기" onclick="check();" /> 
                            <input type="reset"  value="다시쓰기" />
                        </td>
                    </tr>
                </table>
              </form>
        </div>
    </div>
</body>
</html>