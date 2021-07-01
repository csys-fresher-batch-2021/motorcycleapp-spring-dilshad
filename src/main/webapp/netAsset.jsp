<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <!DOCTYPE html>
  <html lang="eng">

  <head>
    <meta charset="ISO-8859-1">
    <title>Asset</title>
  </head>

  <body>
    <jsp:include page="header.jsp"></jsp:include>
    <main class="container-fluid">
      <h3>Current Asset</h3>

      <button type="button" id="submitBtn" ">Get current Asset</button>
      <br/>
      <strong><p id="asset"> </p></strong>
      <script src="js/bikeAssetEventListener.js "></script>
    </main>
  </body>

  </html>