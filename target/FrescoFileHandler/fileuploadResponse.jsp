<%@ page import="com.UploadDetail" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<!--Write your code here-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Servlet File Upload/Download</title>

		<link rel="stylesheet" href="resource/css/main.css" />
	</head>
	<body>
	<div class="panel">
		<h1>File Upload Status</h1>
		<table class="bordered_table">
			<thead>
			<tr align="center"><th>File Name</th><th>File Size</th><th>Upload Status</th><th>Action</th></tr>
			</thead>
			<tbody>
			<% List<UploadDetail> uploadDetails = (List<UploadDetail>)request.getAttribute("uploadedFiles");
				for(int i=0; i<uploadDetails.size(); i++) {
			%>
			<tr>
				<td align="center"><span id="fileName"><%=uploadDetails.get(i).getFileName() %></span></td>
				<td align="center"><span id="fileSize"><%=uploadDetails.get(i).getFileSize() %> KB</span></td>
				<td align="center"><span id="fileuploadStatus"><%=uploadDetails.get(i).getUploadStatus() %></span></td>
				<td align="center"><span id="fileDownload"><a id="downloadLink" class="hyperLink" href="<%=request.getContextPath()%>/downloadServlet?fileName=<%=uploadDetails.get(i).getFileName() %>">Download</a></span></td>
			</tr>
			<% } %>
			</tbody>
		</table>
		<div class="margin_top_15px">
			<a id="index" class="hyperLink" href="<%=request.getContextPath()%>/index.jsp">Back</a>
		</div>
	</div>
	</body>
</html>	