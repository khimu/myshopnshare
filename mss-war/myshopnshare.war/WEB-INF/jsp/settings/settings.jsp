<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<%--$("[name]") Gets each element with an attribute of "name" --%>

<script type="text/javascript">

	$(document).ready(function() { 
		/*
		$.ajax({  
		       type: "POST",  
		       url: "/secure/permission.do",
		       data: {method: 'view'},
		       success: function(data){  
					$('#permission-page').html(data);
					 $("#settings > ul").tabs();
		       } 
		     });
		*/
	     $('#submit-custom-background').click(function(){
			 $.ajax({  
			       type: "post",  
			       url: "/secure/settings.do",
			       data: {method: 'customize', backgroundImage: $('#custom-background-image').val(), backgroundColor: $('#custom-background-color').val()},
			       success: function(data){  
						 $("#settings > ul").tabs();
			       } 
			     });
	     });
		
		 $("#settings > ul").tabs();
		 
			// check if confirm password is still valid after password changed
			$("#password").blur( function() {
				$("#confirm_password").valid();
			});

			$("#change-password-form").validate( {
				rules : {
					password : {
						required :true,
						minlength :4
					},
					confirmPassword : {
						required :true,
						minlength :4,
						equalTo :"#mpassword"
					}
				},
				messages : {
					password : {
						required :"Please provide a password",
						minlength :"Your password must be at least 4 characters long"
					},
					confirmPassword : {
						required :"Please provide a password",
						minlength :"Your password must be at least 5 characters long",
						equalTo :"Please enter the same password as above"
					}
				},
				submitHandler : function(form) {
					jQuery(form).ajaxSubmit( { 
						url: '/secure/settings.do?method=regenerate',
						type: 'post'
					});
				}
			});
	});
</script>


  <style type="text/css">
 
  #settings {
  	height: 100%;
  	width: 100%;
  }
   	
  	#permission-page TABLE {
  		margin: 5px;
  	}
  	
  	#permission-page TD, #permission-page TH {
  		padding: 5px;
  		font-size: 1.0em;
  	}
  </style>
  
        <div id="settings" class="flora">
            <ul>
                <li><a href="#sfragment-1"><span>Main</span></a></li>
                <li><a href="#sfragment-2"><span>Friends</span></a></li>
                <li><a href="#sfragment-3"><span>Groups</span></a></li>
                <li><a href="#sfragment-4"><span>Permissions</span></a></li>
                 <li><a href="#sfragment-5"><span>Change Password</span></a></li>
            </ul>
            
            <div id="sfragment-1" class="fragment-padding">
				<%@ include file="../groups/main.jsp" %>
            </div>
            <div id="sfragment-2" class="fragment-padding">
				<div id="groups-element">
				<%@ include file="manageFriends.jsp" %>
				</div>            
            </div>              
             <div id="sfragment-3">
				<div id="groups-element">
				<%@ include file="../groups/groups.jsp" %>
				</div>            
            </div>           
            <div id="sfragment-4">
			  	<div id="permission-page" style="height: 400px;">
			  	<%@ include file="../permission/permissions.jsp" %>
				</div>				
            </div>         
            <div id="sfragment-5" class="fragment-padding">
 					<form id="change-password-form">
 					<table>
						<tr>
							<td><label for="password">Password</label></td>
							<td><input id="mpassword" name="password" type="password" /></td>
						</tr>
						<tr>
							<td class="field"><label for="confirm_password">Confirm Password</label></td>
							<td class="value"><input id="confirm_password" name="confirmPassword" type="password" /></td>
						</tr>
					</table>
					<div><input type="submit" value="Submit" /></div>
					</form>                  	
            </div>          
        </div>	