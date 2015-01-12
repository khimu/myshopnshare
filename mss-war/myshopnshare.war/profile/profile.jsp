<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<%--$("[name]") Gets each element with an attribute of "name" --%>

  <script type="text/javascript">
	function addEducation(){
		new Boxy('#education-form', {title: 'Add Education', modal: true, afterHide: function(){
			$('#user-education-upload-form').clearForm();
		}});		
	}
	function addEmployment(){
		new Boxy('#employer-form', {title: 'Add Employment', modal: true, afterHide: function(){
			$('#employer-submit-form').clearForm();
		}});		
	}
  
	function deleteFace(recordId){
		Boxy.confirm("Please confirm delete:", function() { //alert('Confirmed!'); 
			$.ajax({
				url: '/secure/face.do',
				type: 'post',
				data: {method: 'delete', recordId : recordId}, 
			    error: function(){
			        alert('Error deleting acount info');
			    },
			    success: function(xml){
				    $('#face-'+recordId).remove();
			    }
			});	
		}, {title: 'Confirm Delete'});
	    return false;						
	}  
	$(document).ready(function() { 
		$('#profile-face-popup').click(function(){
			new Boxy('#profile-face-page', {title: 'Profile Upload', modal: true, afterHide: function(){
				$('#profile-face-form').clearForm();
			}});
		});

		 $.ajax({  
		       type: "POST",  
		       url: "/secure/face.do",
		       dataType: 'json', 
		       success: function(json){  
					$.each(json.faces, function(i,face){						
							$('#new-profile-face').append('<sns:imagerecord caption="'+ face.caption +'" description="'+ face.description +'" thumbnail="'+ face.glimpse +'" icon="'+ face.icon +'" primaryKey="' + face.id + '" defaultPhoto="' + face.defaultFace + '" deleteURL="/secure/face.do?method=" idType="face"  />');
						
			         }); 	      
					$("#profile > ul").tabs();	
					$("a[rel^='prettyPhoto']").prettyPhoto();
		       } 
		     });	
		 $("#profile > ul").tabs();
	});

  </script>
  
  <style type="text/css">
  	.profile-box {
  		padding:5px;
  	}
  	
  	.profile-box li {
  		list-style-type: none;
  	}
  	
  	#new-profile-face li {
  		list-style-type: none;
  		float: left;
  	}
  </style>

        <div id="profile" class="flora">
            <ul>
                <li><a href="#pfragment-1"><span>Profile Picture</span></a></li>
                <li><a href="#pfragment-2"><span>General</span></a></li>
                <li><a href="#pfragment-3"><span>Education</span></a></li>
                <li><a href="#pfragment-4"><span>Employer</span></a></li>
            </ul>
            
            <div id="pfragment-1">
				<div class="profile-box">
					<ul>
						<li>
							<a href="#" id="profile-face-popup"><img src="/images/buttons/uploadprofileimage.png" /></a>
							<div style="display: none;"><%@ include file="face/form.jsp" %></div>
						</li>
						<li>
							<%@ include file="face/face.jsp" %>
						</li>			
					</ul>
				</div>
            </div>
            <div id="pfragment-2">
             	<div class="profile-box">
					<%@ include file="form.jsp" %>
				</div>
            </div>           
            <div id="pfragment-3">
            	<div class="profile-box">
	                <a href="#" onclick="addEducation();"><img src="/images/buttons/add.png" /></a>
	            
				  	<div id="education-form" style="display:none;">	
				  		<%@ include file="education/form.jsp" %>	
				  	</div>            
	            	
				  	<%@ include file="education/education.jsp" %>  
				</DIV>
            </div>       
            <div id="pfragment-4">
            	<div class="profile-box">
            		<a href="#" onclick="addEmployment();"><img src="/images/buttons/add.png" /></a>
	           	 	<div id="employer-form" style="display: none;">
	           	 		<%@ include file="employer/form.jsp" %>
	           	 	</div>            
	            	
	           	 	<%@ include file="employer/employer.jsp" %>
           	 	</div>
            </div>     
        </div>	
		