<%@page import="db.dao.MemberInfoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="db.dao.MemberInfoDAO"%>
<%@ page import="db.dto.MemberInfoDTO"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.side_bar {
	margin-right: 2%;
	width: 10%;
	height: 1200px;
	display: block;
	float: left;
	border: 1px solid black;
}

body {
	color: #666;
	font: 14px/24px "Open Sans", "HelveticaNeue-Light",
		"Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial,
		"Lucida Grande", Sans-Serif;
}

table {
	margin-top: 5%; border-collapse : separate;
	border-spacing: 0;
	width: 88%;
	border-collapse: separate;
}

th, td {
	padding: 8px 4px;
}

th {
	background: #42444e;
	color: #fff;
	text-align: left;
}

tr:first-child th:first-child {
	border-top-left-radius: 6px;
}

tr:first-child th:last-child {
	border-top-right-radius: 6px;
}

td {
	border-right: 1px solid #c6c9cc;
	border-bottom: 1px solid #c6c9cc;
}

td:first-child {
	border-left: 1px solid #c6c9cc;
}

tr:nth-child(even) td {
	background: #eaeaed;
}

tr:last-child td:first-child {
	border-bottom-left-radius: 6px;
}

tr:last-child td:last-child {
	border-bottom-right-radius: 6px;
}

#input_membership_number {
margin-top: 20px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="side_bar"></div>

	<%
	
	/* String membership_number = request.getParameter("membership_number");
	System.out.println(membership_number);
	int intId = 0;
	try {
		intId = Integer.parseInt(membership_number);
	} catch (Exception e){
		e.printStackTrace();
		intId = 0;
	} */
	
		
		MemberInfoDAO memberInfoDAO = new MemberInfoDAO();
		
		//MemberInfoDTO memberInfo = memberInfoDAO.findMemberById(intId);
	
		List<MemberInfoDTO> memberList = memberInfoDAO.findMemberList();
		%>

	<h1>회원정보</h1>
	<p></p>
	<table class="member_graph">
		<tr>
			<th>회원번호</th>
			<th>이름</th>
			<th>나이</th>
			<th>전화번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이메일</th>
			<th>회원등급</th>
			<th>면허증번호</th>
			<th>성별</th>
			<th>생년월일</th>
			<th>면허취득일</th>
			<th>연체횟수</th>
			<th>이용횟수</th>
		</tr>




		<%
		for(MemberInfoDTO memberInfo : memberList) {
						  %>

		<tr>
			<td><%=memberInfo.getMembership_number()%><a href="./Member.jsp?id=<%=memberInfo.getMembership_number()%>"></a></td>
			<td><%=memberInfo.getName()%></td>
			<td><%=memberInfo.getAge()%></td>
			<td><%=memberInfo.getTel()%></td>
			<td><%=memberInfo.getId()%></td>
			<td><%=memberInfo.getPassword()%></td>
			<td><%=memberInfo.getEmail()%></td>
			<td><%=memberInfo.getMembership_level()%><a href="./Member.jsp?id=<%=memberInfo.getMembership_level()%>"></a></td>
			<td><%=memberInfo.getLisence_number()%></td>
			<td><%=memberInfo.getGender()%></td>
			<td><%=memberInfo.getBirthday()%></td>
			<td><%=memberInfo.getLisence_acquisition_date()%></td>
			<td><%=memberInfo.getOverdue_history()%></td>
			<td><%=memberInfo.getUse_count()%></td>
		</tr>
		<%
	  } 
	  %>

	</table>
	




	 <form id="personForm" action="deleteMember_proc.jsp" method="post">
		<label>회원번호 : </label><input type="text" id="input_membership_number" name="membership_number"
		>
		<!-- <label>수정할 회원등급 : </label><input type="text" id="input_membership_level" name="membership_level"> -->
		<button id="deleteBtn" type="button">삭제하기</button>
		<!-- <button id="modifyBtn" type="button">수정하기</button> -->
	</form>
	
	<script>
		document.getElementById('deleteBtn').addEventListener('click',()=> {
			if (confirm('삭제 하시겠습니까?')){
				//location.href = 'deletePerson_proc.jsp?id=';
				
				let form = document.getElementById('personForm');
				form.action = 'deleteMember_proc.jsp';
				form.submit();
			}
		});
		
		document.getElementById('modifyBtn').addEventListener('click',()=>{
			let input_membership_level = document.getElementById('input_membership_level');
			if(input_membership_level.value.trim() == ''){
				alert('회원번호는 필수 입력입니다.');
				input_membership_level.focus();
				return;
			}
			
			if (confirm('수정 하시겠습니까?')){
				let form = document.getElementById('personForm');
				form.action = 'modifyMember_proc.jsp';
				form.submit();
			}
		});
	</script>

</body>
</html>