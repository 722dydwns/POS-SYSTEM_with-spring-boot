<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품 판매</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">

						<!-- 상품명, 수량 입력 시 -> 총가격 계산하기 -->
						<form:form modelAttribute="sellingBean">
							<p>
								<label>상품명 <form:input path="content_name" /></label>
								<form:errors path="content_name" />
								<br /> <label>수량 <form:input path="content_count" /></label>
								<form:errors path="content_name" />

								<input type="submit" value="카드결제">	
								<input type="submit" value="현금결제">							
							</p>
						</form:form>
						<c:if test="${! empty price}">
							<table border="1" bordercolor="black">
								<tr>
									<th class="text-center d-none d-md-table-cell">총 가격</th>
								</tr>
								<tr>
									<td class="text-center d-none d-md-table-cell">${price}원</td>
								</tr>
							</table>
						</c:if>
						
						<a class="btn btn-lg btn-dark" href="${root }board/main?board_info_idx=2"/>판매목록</a>
						
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
			
		</div>
	</div>


	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>


