
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
            <#if subItem['type'] == "Date">
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
                $("#${key}").val("false");
            </#if>
        </#list>
            $( "#createForm" ).submit(function( event ) {
                $("#createForm").each(function(index) {
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
    <#if redirectTo??>
        <form id="createForm" action="" method="POST">
            <input type="hidden" value="${redirectTo}" />
        </form>
        <form id="createForm" action="" method="POST">
    <#else>
        <form id="createForm" action="" method="POST">
    </#if>
        <#list fields as field>
            <#list currentItem?keys as key>
                <#assign subItem = currentItem[key]>
                <#if field == key>
                    <#if field != "id">
                        <#if subItem['type'] == "Date">
                            <br>
                                ${key}
                                <input id="${key}" type="datetime" readonly
                                    name="${key}"
                                    value="" />
                                <input id="${key}date" type="date" value="">
                                <input id="${key}time" type="time" step="1" value="">
                            </br>
                        <#elseif subItem['type'] == "Long" || subItem['type'] == "Integer">
                            <br>
                                ${key}
                                <input type="number"
                                    name="${key}"
                                    value="" />
                            </br>
                        <#elseif subItem['type'] == "Boolean">
                            <br>
                                ${key}
                                <input id="${key}checkbox" type="checkbox"
                                    name="${key}checkbox"
                                    value="" />

                                <input id="${key}" type="hidden"
                                    name="${key}"
                                    value="" />
                            </br>
                        <#else>
                            <br>
                                ${key}
                                <input type="text"
                                    name="${key}"
                                    value="" />
                            </br>
                        </#if>
                    </#if>
                </#if>
            </#list>
        </#list>
        <#list currentItem?keys as key>
            <#assign subItem = currentItem[key]>
            <#if subItem['ManyToOne']??>
                <#if createAssociationRedirect?? && routeCreateAssociation??>
                    <br>
                        ${key} : <a href="../${key}/${routeCreateAssociation}?redirectTo=${createAssociationRedirect}">Add new</a> <a href="../${key}/">Use one</a>
                    </br>
                </#if>
            </#if>
            <#if subItem['OneToMany']??>
                <#if createAssociationRedirect??>
                    <br>
                        ${key} : <a href="../${key}/create?redirectTo=${createAssociationRedirect}">Use existing</a>
                    </br>
                </#if>
            </#if>
            <#if subItem['ManyToMany']??>
                <#if createAssociationRedirect??>
                    <br>
                        ${key} : <a href="../${key}/create?redirectTo=${createAssociationRedirect}">Use existing</a>
                    </br>
                </#if>
            </#if>
            <#if subItem['OneToOne']??>
                <#if createAssociationRedirect??>
                    <br>
                        ${key} : <a href="../${key}/${routeCreateAssociation}?redirectTo=${createAssociationRedirect}">Add new</a> <a href="../${key}/">Use one</a>
                    </br>
                </#if>
            </#if>
        </#list>
        <br>
            <input type="submit" value="submit"/>
        </br>
    </form>
    <#list currentItem?keys as key>
        <#assign subItem = currentItem[key]>
            <#if subItem['ManyToOne']??>
                <h1>${key}</h1>
                <form id="add${key}Form" path="${key}" action="" method="POST">
                    <#assign subCurrentItem = subItem['value']>
                    <#list subCurrentItem?keys as subKey>
                        <br>
                            ${subKey}

                        </br>
                    </#list>
                </form>
        </#if>
    </#list>
    <a href="list">Back</a>
</body>
</html>