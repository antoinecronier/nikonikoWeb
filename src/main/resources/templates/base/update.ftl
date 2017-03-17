
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/custom.css"/>

    <script src="http://code.jquery.com/jquery-3.0.0.min.js"></script>

    <script>
        $(document).ready(function(){
        <#list currentItem?keys as key>
            <#assign subItem = currentItem[key]>
            <#if subItem['type'] = "Date">
                $("#${key}date").change(function(){
                    $("#${key}").val($("#${key}date").val().replace(/\-/g,"/") + " " + $("#${key}time").val());
                });
                $("#${key}time").change(function(){
                    $("#${key}").val($("#${key}date").val().replace(/\-/g,"/") + " " + $("#${key}time").val());
                });
            <#elseif subItem['type'] == "Boolean">
                $("#${key}checkbox").change(function(){
                    $("#${key}").val($("#${key}checkbox").is(':checked') ? "true" : "false");
                });
            </#if>
        </#list>
            $( "#updateForm" ).submit(function( event ) {
                $("#updateForm").each(function(index) {
                    $(this).children().each (function() {
                        if($(this).val() == ""){
                            $(this).prop('disabled', true);
                        }
                    });
                });
            });
        });
    </script>

</head>
<html>
<body>
    <h1>${page}</h1>
    <form id="updateForm" action="" method="POST">
        <#list fields as field>
            <#list currentItem?keys as key>
                <#assign subItem = currentItem[key]>
                <#if key == field>
                    <#if key != "id">
                        <#if subItem['type']?is_sequence>
                            <br>Sequence</br>
                        <#elseif subItem['type'] = "Date">
                            <br>${key} :
                                <#if subItem['value']?is_date>
                                    <input id="${key}" type="datetime" name="${key}" readonly value="${subItem['value']?string("yyyy/MM/dd HH:mm:ss")}">
                                    <input id="${key}date" type="date" value="${subItem['value']?string("YYYY-MM-DD")}">
                                    <input id="${key}time" type="time" value="${subItem['value']?time}">
                                <#else>
                                    <input id="${key}" type="datetime" name="${key}" readonly value="">
                                    <input id="${key}date" type="date" value="">
                                    <input id="${key}time" type="time" value="">
                                </#if>

                            </br>
                        <#elseif subItem['type'] == "Long" || subItem['type'] == "Integer">
                            <br>${key} :
                                <input type="number" name="${key}" value="${subItem['value']}">
                            </br>
                        <#elseif subItem['type'] = "Boolean">
                            <br>${key} :
                                <input id="${key}checkbox" type="checkbox"
                                    name="${key}checkbox" value="${subItem['value']?c}"
                                    <#if subItem['value']?c = "true"> checked="true"</#if>>
                                <input id="${key}" type="hidden" name="${key}" value="${subItem['value']?c}}">
                            </br>
                        <#else>
                            <br>${key} :
                                <input type="text" name="${key}" value="${subItem['value']}">
                            </br>
                        </#if>
                    <#else>
                        <input type="hidden" name="id" value="${subItem['value']}">
                    </#if>
                </#if>
            </#list>
        </#list>

        <br>
            <input type="submit" value="update"/>
        </br>
    </form>
    <a href="../list">Back</a>
</body>
</html>