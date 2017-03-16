
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/custom.css"/>
</head>
<html>
<body>
    <h1>${page}</h1>
    <form action="" method="POST">
        <#list fields as field>
            <#list currentItem?keys as key>
                <#if field == key>
                    <#if field != "id">
                        <#if currentItem[key]?is_datetime>
                            <br>
                                Name:${key}
                                <input type="text"
                                    name="${key}"
                                    value="" />
                                <input id="${key}date" type="date" value="${currentItem[key]?string("YYYY-MM-DD")}">
                                <input id="${key}time" type="time" value="${currentItem[key]?time}">
                            </br>
                        <#else>
                            <br>
                                Name:${key}
                                <input type="text"
                                    name="${key}"
                                    value="" />
                            </br>
                        </#if>
                    </#if>
                </#if>
            </#list>
        </#list>
        <br>
            <input type="submit" value="submit"/>
        </br>
    </form>
    <a href="list">Back</a>
</body>
</html>