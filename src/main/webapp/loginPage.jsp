<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <style>
            .required {
                color: red;
            }
        </style>
        <meta charset="ISO-8859-1">
        <title>Login</title>
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <main class="container-fluid">
            <br>
            <h2>Login</h2>
            <p id="errorBlock"></p>
            <form onsubmit=login()>
                <label for="uname">Admin ID:</label>
                <span class="required">*</span>
                <br>
                <input type="text" id="emailId" name="emailId" required="required" autofocus="autofocus" placeholder="Enter your Email ID">
                <br>
                <br>
                <label for="pass">Access Key</label>
                <span class="required">*</span>
                <br>
                <input type="password" id="password" name="password" required="required" placeholder="Enter access key">
                <br>
                <br>
                <button class="btn btn-primary">Enter</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
                <br>
                <br>
            </form>
            Not yet Registered?
            <a href="memberRegistration.jsp"> Click here to register
            </a>
        </main>
        <script>
            function login() {
                event.preventDefault();
                let emailID = $("#emailId").val();
                let password = $("#password").val();

                let credential = {
                    "email": emailID,
                    "password": password
                };
                console.log(credential);

                let url = "motorcycleapp/v1/auth/member/login";
                let content = "";
                axios.post(url, credential).then(res => {
                    let data = res.data;
                    console.log("Data: " + data.name);
                    localStorage.setItem("LOGGED_IN_MEMBER", JSON.stringify(data));
                    alert("Welcome, Sucessfully Logged in");
                    window.location.href = "index.jsp";

                }).catch(err => {
                    let data = err.response.data;
                    if (data.errorMessage != null) {
                        console.log("Exception:" + data.errorMessage);
                        document.querySelector("#errorBlock").innerHTML = "Unable to login";
                    }
                })
            }
        </script>
    </body>

    </html>