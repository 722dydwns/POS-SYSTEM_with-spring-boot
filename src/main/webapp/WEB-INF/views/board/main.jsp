<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
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

	<!-- 게시글 리스트 -->
	<div class="container" style="margin-top: 100px">
		<div class="card shadow">
			<div class="card-body">
				<h4 class="card-title">${boardInfoName}</h4>

				<!-- 재고관리 시에만 뜨게 해주기 -->
				<c:if test="${A eq '1'}">
					<a class="btn btn-lg btn-dark" href="${root }board/search">상품검색</a>

					<table class="table table-hover" id='board_list'>
						<thead>
							<tr>
								<th class="text-center d-none d-md-table-cell">목록번호</th>
								<th class="text-center d-none d-md-table-cell">상품명</th>
								<th class="text-center d-none d-md-table-cell">가격</th>
								<th class="text-center d-none d-md-table-cell">수량</th>
								<th class="text-center d-none d-md-table-cell">등록날짜</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var='obj' items="${contentList }">
								<tr>
									<td class="text-center d-none d-md-table-cell">${obj.content_idx }</td>
									<td class="text-center d-none d-md-table-cell">${obj.content_name }</td>
									<td class="text-center d-none d-md-table-cell">${obj.content_price }</td>
									<td class="text-center d-none d-md-table-cell">${obj.content_count }</td>
									<td class="text-center d-none d-md-table-cell">${obj.content_date }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="text-right">
						<a href="${root }board/write?board_info_idx=${board_info_idx}"
							class="btn btn-primary">상품 추가하기</a>
					</div>

				</c:if>

				<!-- 판매하기의 메인읠 경우  -->
				<c:if test="${A eq '2'}">
					<a class="btn btn-lg btn-dark" href="${root }board/selling">판매하기</a>

					<table class="table table-hover" id='board_list'>
						<thead>
							<tr>
								<th class="text-center d-none d-md-table-cell">목록번호</th>
								<th class="text-center d-none d-md-table-cell">판매상품명</th>
								<th class="text-center d-none d-md-table-cell">판매금액</th>
								<th class="text-center d-none d-md-table-cell">판매날짜</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var='sellingList' items="${sellingList }">
								<tr>
									<td class="text-center d-none d-md-table-cell">${sellingList.selling_idx }</td>
									<td class="text-center d-none d-md-table-cell">${sellingList.selling_name }</td>
									<td class="text-center d-none d-md-table-cell">${sellingList.selling_price }</td>
									<td class="text-center d-none d-md-table-cell">${sellingList.selling_date }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</c:if>

				<!-- 판매통계 메인읠 경우  -->
				<c:if test="${A eq '3'}">
					<a class="btn btn-lg btn-dark" href="${root }board/oneWeekTotal"/>최근 일주일 통계 </a>
					<a class="btn btn-lg btn-dark" href="${root }board/oneMonthTotal"/>최근 한달 통계 </a>
					<a class="btn btn-lg btn-dark" href="${root }board/Total"/>지정 기간 통계 </a>
				</c:if>

			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>






