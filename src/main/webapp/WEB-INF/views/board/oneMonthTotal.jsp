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
<title>최근 한달 판매 총액</title>
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

						<c:if test="${! empty price}">
							<table border="1" bordercolor="black">
								<tr>
									<th class="text-center d-none d-md-table-cell">최근 한달 판매 총액</th>
								</tr>
								<tr>
									<td class="text-center d-none d-md-table-cell">${price}원</td>
								</tr>
							</table>
						</c:if>
						
						<table class="table table-hover" id='board_list'>
						<thead>
							<tr>
								<th class="text-center d-none d-md-table-cell">판매날짜</th>
								<th class="text-center d-none d-md-table-cell">판매가격</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var='oneMonthList' items="${oneMonthList }">
								<tr>
									<td class="text-center d-none d-md-table-cell">${oneMonthList.selling_date }</td>
									<td class="text-center d-none d-md-table-cell">${oneMonthList.selling_price }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
						<a class="btn btn-lg btn-dark" href="${root }board/main?board_info_idx=3"/>되돌아가기</a>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>


	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>