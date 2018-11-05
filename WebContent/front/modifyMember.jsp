<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>

<jsp:useBean id="conn" scope="page" class="com.tools.ConnDB" />
<jsp:useBean id="chStr" scope="page" class="com.tools.ChStr" />
<%
	String username = (String) session.getAttribute("username");
	if (username != null && username != "") {
		String sql = "select * from t_user where username='" + username + "'";
		ResultSet rs = conn.executeQuery(sql);
		int id=-1;
		
		String password="";
		String email="";
		String phone="";
		String qq="";
				
		if (rs.next()) {
			id = rs.getInt("id");
			
			System.out.println(username);
			password = rs.getString("password");
			email = rs.getString("email");
			phone = rs.getString("phone");
			qq = rs.getString("qq");
			
		}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>图书列表-巨石商城</title>
<link rel="stylesheet" href="css/mr-01.css" type="text/css">
</head>

<body>
	<jsp:include page="index-loginCon.jsp" />




	<div id="mr-mainbody" class="container mr-mainbody">
		<div class="row">
			<!-- MAIN CONTENT -->
			<div id="mr-content" class="mr-content col-xs-12">
				<div id="system-message-container" style="display: none;"></div>

				<div class="registration col-sm-6 col-sm-offset-3">

						<!-- 会员注册表单 -->
					<form id="member-registration" action="modifyMember_deal.jsp"
						onsubmit="return regis();" method="post"
						class="form-horizontal">

							<fieldset>
								<legend style="color: #929292; font-size: 26px">修改用户信息</legend>
								<div class="form-group">
									<div class="col-sm-4 control-label">
										<label id="username-lbl" for="username" class="required">
											账户 ：</label>
									</div>
									<div class="col-sm-8">
										<!-- 账户文本框 -->
										<input type="text" name="username" id="username"
									value="<%=username%>" class="required" size="38"
									aria-required="true"><span class="star">&nbsp;*</span>
									</div>
								</div>
								
							


								<div class="form-group">
									<div class="col-sm-4 control-label">
										<label id="password-lbl" for="password" class="required">
											新密码 ：</label>
									</div>
									<div class="col-sm-8">
										<!-- 密码文本框 -->
										<input type="password" name="password" id="newPwd" value=""
									autocomplete="off" class="validate-password required" size="38"
									maxlength="99" aria-required="true"><span class="star">&nbsp;*</span>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-4 control-label">
										<label id="password-lbl" for="password" class="required">
											联系电话 ：</label>
									</div>
									<div class="col-sm-8" style="clear: none;">
										<!-- 输入联系电话的文本框 -->
										<input type="text" name="phone" class="validate-email " id="tel"
									value="<%=phone%>" size="38" autocomplete="email" aria-required="true">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 control-label">
										<label id="password-lbl" for="password" class="required">
											QQ号 ：</label>
									</div>
									<div class="col-sm-8" style="clear: none;">
										<!-- 输入联系电话的文本框 -->
										<input type="text" name="qq" class="validate-email " id="tel"
									value="<%=qq%>" size="38" autocomplete="email" aria-required="true">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 control-label">
										<label id="password-lbl" for="password" class="required">
											邮箱 ：</label>
									</div>
									<div class="col-sm-8" style="clear: none;">
										<!-- 输入邮箱的文本框 -->
										<input type="email" name="email" class="validate-email"
									id="jform_email2" value="<%=email%>" size="38"
									aria-required="true">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-8" style="margin-left: 150px;"><input name="ID" type="hidden" value="<%=id%>">
										<button type="submit" class="btn btn-primary pull-left" style="width:100px;">提交</button>
							<button type="button" onclick="javascript:history.go(-1)"
								class="btn btn-primary pull-left" style="width:100px;">取消</button>
									</div>
								</div>
							</fieldset>
						</form>
				</div>

			</div>
			<!-- //MAIN CONTENT -->

		</div>
	</div>

	<%@ include file="common-footer.jsp"%>

</body>
<script src="js/jquery.1.3.2.js" type="text/javascript"></script>
<script>
	function regis() {

		if (/^[\u4e00-\u9fa5]+$/.test($('#username').val())) {
			alert("账户不能输入汉字！");
			return false;
		}

		if (isNaN($('#tel').val())) {
			alert("联系电话请输入数字");
			return false;
		}

		
		return true;
	}
</script>
</html>
<%
	conn.close();
	} else {
		out.println(
				"<script lanage='javascript'>alert('请先登录，再修改个人信息!');window.location.href='index.jsp';</script>");
	}
%>