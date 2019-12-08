<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shopping/css/style.css"/>
<script src="/shopping/js/jquery-1.11.0.min.js"></script>
<script src="/shopping/js/jquery.form.min.js"></script>
<script src="/shopping/mngr/productProcess/bookregist.js"></script>

<%-- <c:if test="${empty sessionScope.id}"> --%>
<!--   <meta http-equiv="Refresh" content="0;url=/shopping/mg/managerMain.do" > -->
<%-- </c:if> --%>

<div id="listHeader">
  <button id="bookMain">관리자 메인으로</button>
  <button id="bookList">목록으로</button>
</div>
<form id="upForm1" action="/shopping/mg/gameRegisterPro.do" 
          method="post" enctype="multipart/form-data">
<div id="bookRegistForm" class="box">
   <ul>
      <li><label for="kind">분류선택</label>
          <select id="kind" name="kind">
            <option value="RTS">RTS</option>
            <option value="FPS">FPS</option>
            <option value="ACT">액션</option>
            <option value="RPG">RPG</option>
            
          </select>
      <li><label for="title">제목</label>
          <input id="title" name="title" type="text" 
           size="50" placeholder="제목" maxlength="50">
      <li><label for="pro_desc">내용</label>
          <textarea id="pro_desc" name="pro_desc" rows="13" cols="50"></textarea>
      <li>
          <table>
			<thead>
				<tr>
					<th colspan="2">최소 사양</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>OS</td>
					<td><input type="text" name="mos" /></td>
				</tr>
				<tr>
					<td>CPU</td>
					<td><input type="text" name="mcpu" /></td>
				</tr>
				<tr>
					<td>Memory</td>
					<td><input type="text" name="mmem" /></td>
				</tr>
				<tr>
					<td>VGA</td>
					<td><input type="text" name="mvga" /></td>
				</tr>
				<tr>
					<td>Direct X</td>
					<td><input type="text" name="mdirectx" /></td>
				</tr>
				<tr>
					<td>Disk Storage</td>
					<td><input type="text" name="mdisk_storage" /></td>
				</tr>
			</tbody>
		</table>
      <li><table>
			<thead>
				<tr>
					<th colspan="2">권장 사양</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>OS</td>
					<td><input type="text" name="hos" /></td>
				</tr>
				<tr>
					<td>CPU</td>
					<td><input type="text" name="hcpu" /></td>
				</tr>
				<tr>
					<td>Memory</td>
					<td><input type="text" name="hmem" /></td>
				</tr>
				<tr>
					<td>VGA</td>
					<td><input type="text" name="hvga" /></td>
				</tr>
				<tr>
					<td>Direct X</td>
					<td><input type="text" name="hdirectx" /></td>
				</tr>
				<tr>
					<td>Disk Storage</td>
					<td><input type="text" name="hdisk_storage" /></td>
				</tr>
			</tbody>
		</table>         
      <li><label for="price">가격</label>
          <input id="price" name="price" type="text" 
           size="10" placeholder="가격" maxlength="9">원
      <li><label for="publishing_date">발매일</label>
          <div id="publishing_date"> 
            <jsp:useBean id="nowTime" class="java.util.Date"></jsp:useBean>
            <fmt:formatDate var="nowTimeStr" pattern="yyyy-MM-dd" value="${nowTime}" />
            <fmt:parseNumber var="lastYear" type="NUMBER" value="${nowTimeStr.toString().substring(0,4)}"/>
            <select name="publishing_year">
              <c:forEach var="i" begin="2010" end="${lastYear}">
                <option value="${i}">${i}</option>
              </c:forEach>
           </select>년
           <select name="publishing_month">
             <c:forEach var="i" begin="1" end="12">
                <option value="${i}">${i}</option>
             </c:forEach>
           </select>월
           <select name="publishing_day">
             <c:forEach var="i" begin="1" end="31">
                <option value="${i}">${i}</option>
             </c:forEach>
           </select>일
          </div>
      <li><label for="grade">게임등급</label>
           <input id="grade" name="grade" type="text" size="10" placeholder="12" maxlength="5">
      <li><label for="begin_date">마감할인시작일</label>
		  <input id="begin_date" name="begin_date" type="text" size="20" placeholder="1995-12-07" maxlength="10">  
      <li><label for="end_date">마감할인끝일</label>
          <input id="end_date" name="end_date" type="text" size="20" placeholder="1995-12-07" maxlength="10"> 
      <li><label for="book_image">게임 이미지</label>
          <input id="book_image" name="book_image" type="file">  
      <li class="label2">
          <input type="submit" id="registBook" value="게임등록">
   </ul>
</div>
</form>
