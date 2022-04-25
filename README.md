# Web Page For Administration

## Description

## Why JSP

## Usful Scripts
**Java Code Inside JSP**
```
<% java code %>
```

**Form/HTML**
```
<form action="name.jsp">
<form action="name.html">
<form action="servlet">
```

**JSP Expression Tag**
dealing with variables
```
<%= statement %>
<%= "welcom in Web Page" %>
<%= "java.util.Calendar.getInstance().getTime()" %>
```

**JSP Decralation Tag**
dealing with variables and method
```
<%! Method %>
```

**JSP REQUEST**
```
<% String name = request.getParameter("parameterName in form");
   out.print("Welcom: "+name);%>
```

**JSP RESPONSE**
```
<% response.sendRedirect("URL"); %>
```

**Java Page Directive**
```
<%@ directive attribute = "value" %>
<%@ page import = "java.util.Date" %>
<%@ page contentType = "text/html" %>
<%@ page contentType = "text/blain" %>
<%@ page contentType = "text/xml" %>
<%@ include file = "header.html" %>
<%@ include file = "footer.html" %>
```

**JSP:Forward**
```
<jsp:forward page = "name.jsp">
<jsp:forward page = "name.html">
<jsp:forward page = "servlet">
```


## Contributers


