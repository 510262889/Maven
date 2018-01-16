<%@page import="com.common.chart.EchartArea"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>demo</title>
<script src="${JS_PATH}/jquery.min.js"></script>
<script src="${JS_PATH}/component/echarts/echarts-all.js"></script>
</head>
<body>
	<div id="area" style="display: inline-block; height: 300px; width: 49%"></div>
	<div id="bar" style="display: inline-block; height: 300px; width: 49%"></div>
	${areaJs} ${barJs}
</body>
</html>