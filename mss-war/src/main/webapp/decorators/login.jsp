
		<!-- Start of User Login -->

		<div class="box_container">
		<div id="userlogin">

			<h2><span>User Login</span></h2>

			<form name="loginForm" class="cmxform" action="/j_spring_security_check" method="post">
				<div class="form_field">
				<strong>Username</strong>
				<input type="text" id="j_username" name="j_username" title="Please enter your email address" class="required email" minlength="6" />
				</div>

				<div class="clearthis">&nbsp;</div>

				<div class="form_field">
				<strong>Password</strong>
				<input type="password" name="j_password" id="j_password" class="required" minlength"5" />
				</div>

				<div class="form_field">
						<p id="checkboxStyle" style="display: none;">
							<input type="checkbox" id="cb"
								name="_spring_security_remember_me"
								checked="checked" /> 
								<label for="_spring_security_remember_me">Stay logged in </label>
						</p>
				</div>

				<div class="clearthis">&nbsp;</div>

				<div class="form_field">
				<input type="image" src="images/userlogin_search.gif" class="button" />
				</div>

			</form>

			<div id="link-password">
			<a href="http://www.freewebsitetemplates.com/">Forgot Password</a>
			</div>

		</div>
		</div>

		<!-- End of User Login -->
