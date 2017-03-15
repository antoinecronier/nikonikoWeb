
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
        <#list currentItem?keys as key>
            <#if key != "id">
                <#if currentItem[key]?is_boolean>
                    <br>${key} :
                        <input type="text" name="${key}" value="${currentItem[key]?c}">
                    </br>
                <#elseif currentItem[key]?is_sequence>
                    <br>Sequence</br>
                <#else>
                    <br>${key} :
                        <input type="text" name="${key}" value="${currentItem[key]}">
                    </br>
                </#if>
            <#else>
                <input type="hidden" name="id" value="${currentItem[key]}">
            </#if>
        </#list>

        <br>
            <input type="submit" value="update"/>
        </br>
    </form>
    <a href="../list">Back</a>
</body>
</html>